package com.example.newleaf

import android.net.Uri
import androidx.camera.core.Camera
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Camera
//import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.navigation.NavController

@Composable
fun LeafDiseaseDetectionScreen(
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onCaptureImage: () -> Unit,
    onDetectDisease: () -> Unit,
    resultText: String,
    diseaseName: String,
    diseaseImage: String,
    diseaseDescription: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // App Introduction Section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "Plant Leaf Disease Detector",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Upload a leaf image to identify diseases and get treatment advice.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Image preview
            if (imageUri != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .shadow(8.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Selected Leaf",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Main Action Button
            Button(
                onClick = onPickImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoLibrary,
                    contentDescription = "Select Image",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Select Leaf Image",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Secondary Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    onClick = onCaptureImage,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = "Camera"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Camera")
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                OutlinedButton(
                    onClick = onDetectDisease,
                    enabled = imageUri != null,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Detect Disease")
                }
            }

            // Result Section
            if (resultText == "Prediction pending...") {
                CircularProgressIndicator()
            } else if (resultText.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = diseaseName,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        if (diseaseImage.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(diseaseImage),
                                contentDescription = "Disease Image",
                                modifier = Modifier
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Text(
                            text = diseaseDescription,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                navController.navigate("treatment")
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("View Treatment Guide")
                        }
                    }
                }
            }
            
            // Add some bottom padding for better scrolling experience
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}