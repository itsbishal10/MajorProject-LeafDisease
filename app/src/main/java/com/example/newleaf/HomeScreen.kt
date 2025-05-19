package com.example.newleaf

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newleaf.ui.theme.DarkGreen40

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val forestGreen = Color(0xFF228B22)
    
    // Animation states
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    // Background gradient
    val gradient = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background,
            forestGreen.copy(alpha = 0.05f)
        )
    )
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradient)
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Welcome Section with animation
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = forestGreen
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.LocalFlorist,
                    contentDescription = "Leaf Icon",
                    modifier = Modifier
                        .size(48.dp)
                        .scale(scale),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Welcome to LEAFGUARD",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your Plant Health Companion",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        // How to Use Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "How to Use",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                
                // Step 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Camera Icon
                    Card(
                        modifier = Modifier
                            .size(64.dp)
                            .animateContentSize(),
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = forestGreen
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Camera,
                                contentDescription = "Take Photo",
                                modifier = Modifier.size(32.dp),
                                tint = Color.White
                            )
                        }
                    }
                    
                    // Arrow
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp),
                        tint = forestGreen
                    )
                    
                    // Disease Icon
                    Card(
                        modifier = Modifier
                            .size(64.dp)
                            .animateContentSize(),
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = forestGreen
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFlorist,
                                contentDescription = "Disease Detection",
                                modifier = Modifier.size(32.dp),
                                tint = Color.White
                            )
                        }
                    }
                    
                    // Arrow
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Next",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp),
                        tint = forestGreen
                    )
                    
                    // Solution Icon
                    Card(
                        modifier = Modifier
                            .size(64.dp)
                            .animateContentSize(),
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = forestGreen
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Spa,
                                contentDescription = "Get Solution",
                                modifier = Modifier.size(32.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Step Labels
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Take Photo",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (MaterialTheme.colorScheme.background == Color.White) 
                            DarkGreen40 else Color.White
                    )
                    Text(
                        text = "Get Diagnosis",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (MaterialTheme.colorScheme.background == Color.White) 
                            DarkGreen40 else Color.White
                    )
                    Text(
                        text = "Get Solution",
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (MaterialTheme.colorScheme.background == Color.White) 
                            DarkGreen40 else Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Start Button with animation
                Button(
                    onClick = { navController.navigate("detection") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .animateContentSize(),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = forestGreen,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = "Start",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Take/Choose a Picture",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        // Quick Tips Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Tips",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Quick Tips",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "• Ensure good lighting when taking photos\n" +
                          "• Capture the entire affected area\n" +
                          "• Keep leaves clean and dry\n" +
                          "• Regular inspection helps early detection",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
} 