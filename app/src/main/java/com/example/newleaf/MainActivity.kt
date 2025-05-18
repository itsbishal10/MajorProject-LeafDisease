package com.example.newleaf

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newleaf.ui.theme.NewLeafTheme
import java.io.InputStream

class MainActivity : ComponentActivity() {
    private lateinit var tfliteModel: TFLiteModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tfliteModel = TFLiteModel(this)

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val context = LocalContext.current

            var imageUri: Uri? by remember { mutableStateOf(null) }
            var resultText by remember { mutableStateOf("") }
            var diseaseName by remember { mutableStateOf("") }
            var diseaseImage by remember { mutableStateOf("") }
            var diseaseDescription by remember { mutableStateOf("") }

            val pickImageLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri ->
                imageUri = uri
            }

            NewLeafTheme{
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentRoute) {
                                        "home" -> "Leaf Disease Detector"
                                        "treatment" -> "Treatment Guide"
                                        "diseaseInfo" -> "Disease Information"
                                        "datasetInfo" -> "Dataset Information"
                                        else -> "Leaf Disease Detector"
                                    },
                                    style = MaterialTheme.typography.titleLarge
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            navigationIcon = {
                                if (currentRoute != "home") {

                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(Icons.Default.ArrowBack, "Back")
                                    }

                                } else null
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController, currentRoute)
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("home") {
                            LeafDiseaseDetectionScreen(
                                imageUri = imageUri,
                                onPickImage = { pickImageLauncher.launch("image/*") },
                                onDetectDisease = {
                                    resultText = "Prediction pending..."
                                    try {
                                        // Convert URI to Bitmap
                                        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri!!)
                                        val bitmap = BitmapFactory.decodeStream(inputStream)
                                        
                                        // Perform prediction
                                        val prediction = tfliteModel.classifyImage(bitmap)
                                        
                                        // Update UI with results
                                        resultText = "Prediction complete!"
                                        diseaseName = if (prediction.isHealthy) {
                                            "${prediction.plantType} is healthy"
                                        } else {
                                            "${prediction.plantType} - ${prediction.diseaseType}"
                                        }
                                        diseaseImage = "image_url" // You can add specific images for each disease
                                        diseaseDescription = "Confidence: ${(prediction.confidence * 100).toInt()}%"
                                    } catch (e: Exception) {
                                        resultText = "Error: ${e.message}"
                                    }
                                },
                                resultText = resultText,
                                diseaseName = diseaseName,
                                diseaseImage = diseaseImage,
                                diseaseDescription = diseaseDescription
                            )
                        }
                        composable("treatment") {
                            TreatmentScreen(diseaseName = diseaseName)
                        }
                        composable("diseaseInfo") {
                            DiseaseInfoScreen()
                        }
                        composable("datasetInfo") {
                            DatasetInfoScreen()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tfliteModel.close()
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Disease Info") },
            label = { Text("Diseases") },
            selected = currentRoute == "diseaseInfo",
            onClick = { navController.navigate("diseaseInfo") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Dataset Info") },
            label = { Text("Dataset") },
            selected = currentRoute == "datasetInfo",
            onClick = { navController.navigate("datasetInfo") }
        )
    }
}



