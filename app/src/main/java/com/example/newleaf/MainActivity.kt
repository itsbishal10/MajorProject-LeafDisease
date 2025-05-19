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
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
            var isDarkMode by remember { mutableStateOf(false) }
            
            NewLeafTheme(darkTheme = isDarkMode) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val context = LocalContext.current

                var imageUri: Uri? by remember { mutableStateOf(null) }
                var resultText by remember { mutableStateOf("") }
                var diseaseName by remember { mutableStateOf("") }
                var diseaseImage by remember { mutableStateOf("") }
                var diseaseDescription by remember { mutableStateOf("") }

                // Launcher for picking image from gallery
                val pickImageLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent()
                ) { uri ->
                    imageUri = uri
                }

                // Launcher for capturing image from camera
                val cameraLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.TakePicturePreview()
                ) { bitmap: Bitmap? ->
                    if (bitmap != null) {
                        try {
                            resultText = "Prediction pending..."
                            val prediction = tfliteModel.classifyImage(bitmap)

                            resultText = "Prediction complete!"
                            diseaseName = if (prediction.isHealthy) {
                                "${prediction.plantType}___healthy"
                            } else {
                                "${prediction.plantType}___${prediction.diseaseType}"
                            }
                            diseaseImage = "image_url"
                            diseaseDescription = "Confidence: ${(prediction.confidence * 100).toInt()}%"
                            imageUri = null
                        } catch (e: Exception) {
                            resultText = "Error: ${e.message}"
                        }
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentRoute) {
                                        "home" -> "LEAFGUARD"
                                        "detection" -> "Disease Detection"
                                        "treatment" -> "Treatment Guide"
                                        "diseaseInfo" -> "Disease Information"
                                        "datasetInfo" -> "Dataset Information"
                                        else -> "LEAFGUARD"
                                    },
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White,
                                actionIconContentColor = Color.White
                            ),
                            navigationIcon = {
                                if (currentRoute != "home") {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                                    }
                                }
                            },
                            actions = {
                                IconButton(onClick = { isDarkMode = !isDarkMode }) {
                                    Icon(
                                        imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                        contentDescription = "Toggle theme",
                                        tint = Color.White
                                    )
                                }
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
                        composable(
                            route = "home",
                            enterTransition = { fadeIn() + slideInHorizontally() },
                            exitTransition = { fadeOut() + slideOutHorizontally() }
                        ) {
                            HomeScreen(navController = navController)
                        }
                        composable(
                            route = "detection",
                            enterTransition = { fadeIn() + slideInHorizontally() },
                            exitTransition = { fadeOut() + slideOutHorizontally() }
                        ) {
                            LeafDiseaseDetectionScreen(
                                imageUri = imageUri,
                                onPickImage = { pickImageLauncher.launch("image/*") },
                                onCaptureImage = { cameraLauncher.launch(null) },
                                onDetectDisease = {
                                    resultText = "Prediction pending..."
                                    try {
                                        val inputStream: InputStream? =
                                            context.contentResolver.openInputStream(imageUri!!)
                                        val bitmap = BitmapFactory.decodeStream(inputStream)

                                        val prediction = tfliteModel.classifyImage(bitmap)

                                        resultText = "Prediction complete!"
                                        diseaseName = if (prediction.isHealthy) {
                                            "${prediction.plantType}___healthy"
                                        } else {
                                            "${prediction.plantType}___${prediction.diseaseType}"
                                        }
                                        diseaseImage = "image_url"
                                        diseaseDescription =
                                            "Confidence: ${(prediction.confidence * 100).toInt()}%"
                                    } catch (e: Exception) {
                                        resultText = "Error: ${e.message}"
                                    }
                                },
                                resultText = resultText,
                                diseaseName = diseaseName,
                                diseaseImage = diseaseImage,
                                diseaseDescription = diseaseDescription,
                                navController = navController
                            )
                        }
                        composable(
                            route = "treatment",
                            enterTransition = { fadeIn() + slideInHorizontally() },
                            exitTransition = { fadeOut() + slideOutHorizontally() }
                        ) {
                            TreatmentScreen(diseaseName = diseaseName.replace(" ", "_"))
                        }
                        composable(
                            route = "diseaseInfo",
                            enterTransition = { fadeIn() + slideInHorizontally() },
                            exitTransition = { fadeOut() + slideOutHorizontally() }
                        ) {
                            DiseaseInfoScreen()
                        }
                        composable(
                            route = "datasetInfo",
                            enterTransition = { fadeIn() + slideInHorizontally() },
                            exitTransition = { fadeOut() + slideOutHorizontally() }
                        ) {
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
            icon = { 
                Icon(
                    imageVector = if (currentRoute == "home") Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = if (currentRoute == "detection") Icons.Filled.Camera else Icons.Outlined.Camera,
                    contentDescription = "Detect"
                )
            },
            label = { Text("Detect") },
            selected = currentRoute == "detection",
            onClick = { navController.navigate("detection") }
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = if (currentRoute == "diseaseInfo") Icons.Filled.LocalFlorist else Icons.Outlined.LocalFlorist,
                    contentDescription = "Diseases"
                )
            },
            label = { Text("Diseases") },
            selected = currentRoute == "diseaseInfo",
            onClick = { navController.navigate("diseaseInfo") }
        )
        NavigationBarItem(
            icon = { 
                Icon(
                    imageVector = if (currentRoute == "datasetInfo") Icons.Filled.Info else Icons.Outlined.Info,
                    contentDescription = "Dataset"
                )
            },
            label = { Text("Dataset") },
            selected = currentRoute == "datasetInfo",
            onClick = { navController.navigate("datasetInfo") }
        )
    }
}


