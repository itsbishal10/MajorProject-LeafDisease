package com.example.newleaf

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteModel(private val context: Context) {
    private var interpreter: Interpreter? = null
    private val modelPath = "leafNew.tflite"
    private val inputSize = 224 // Assuming your model expects 224x224 input
    private val numChannels = 3 // RGB
    private val numBytesPerChannel = 4 // Float32
    private val numClasses = 20 // Updated to correct number of classes

    private val diseaseClasses = listOf(
        "Apple___Apple_scab",
        "Apple___healthy",
        "Cherry_(including_sour)___Powdery_mildew",
        "Cherry_(including_sour)___healthy",
        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot",
        "Corn_(maize)___Northern_Leaf_Blight",
        "Corn_(maize)___healthy",
        "Grape___Esca_(Black_Measles)",
        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)",
        "Grape___healthy",
        "Peach___Bacterial_spot",
        "Peach___healthy",
        "Pepper,_bell___Bacterial_spot",
        "Pepper,_bell___healthy",
        "Potato___Late_blight",
        "Potato___healthy",
        "Strawberry___Leaf_scorch",
        "Strawberry___healthy",
        "Tomato___Late_blight",
        "Tomato___healthy"
    )

    init {
        interpreter = Interpreter(loadModelFile())
    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun classifyImage(bitmap: Bitmap): PredictionResult {
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, inputSize, inputSize, true)
        val byteBuffer = convertBitmapToByteBuffer(resizedBitmap)
        
        // Output array to store the prediction results
        val result = Array(1) { FloatArray(numClasses) }

        interpreter?.run(byteBuffer, result)

        // Get the index of the highest probability
        val maxIndex = result[0].indices.maxByOrNull { result[0][it] } ?: 0
        val confidence = result[0][maxIndex]

        // Get the disease name and plant type
        val diseaseName = diseaseClasses[maxIndex]
        val (plantType, diseaseType) = parseDiseaseName(diseaseName)

        return PredictionResult(
            plantType = plantType,
            diseaseType = diseaseType,
            confidence = confidence,
            isHealthy = diseaseName.contains("healthy", ignoreCase = true)
        )
    }

    private fun parseDiseaseName(diseaseName: String): Pair<String, String> {
        val parts = diseaseName.split("___")
        val plantType = parts[0]
        val diseaseType = if (parts.size > 1) {
            parts[1]
        } else {
            "Unknown"
        }
        return Pair(plantType, diseaseType)
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(inputSize * inputSize * numChannels * numBytesPerChannel)
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(inputSize * inputSize)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixel in pixels) {
            // Normalize pixel values to [0, 1]
            byteBuffer.putFloat(((pixel shr 16 and 0xFF) - 127.5f) / 127.5f) // R
            byteBuffer.putFloat(((pixel shr 8 and 0xFF) - 127.5f) / 127.5f)  // G
            byteBuffer.putFloat(((pixel and 0xFF) - 127.5f) / 127.5f)        // B
        }

        return byteBuffer
    }

    fun close() {
        interpreter?.close()
    }
}

data class PredictionResult(
    val plantType: String,
    val diseaseType: String,
    val confidence: Float,
    val isHealthy: Boolean
) 