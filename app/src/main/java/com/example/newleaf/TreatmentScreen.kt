package com.example.newleaf

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class TreatmentInfo(
    val plantName: String,
    val diseaseName: String,
    val solutions: List<String>,
    val maintenance: List<String>
)

@Composable
fun TreatmentScreen(diseaseName: String) {
    val treatmentInfo = getTreatmentInfo(diseaseName)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = treatmentInfo.plantName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = treatmentInfo.diseaseName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        if (treatmentInfo.solutions.isNotEmpty()) {
            Text(
                text = "Treatment Solutions:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            treatmentInfo.solutions.forEach { solution ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = "• $solution",
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (treatmentInfo.maintenance.isNotEmpty()) {
            Text(
                text = "Maintenance Tips:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            treatmentInfo.maintenance.forEach { tip ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = "• $tip",
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

private fun getTreatmentInfo(diseaseName: String): TreatmentInfo {
    // Clean up the disease name by replacing underscores with spaces for display
    val displayName = diseaseName.replace("_", " ")
    
    return when (diseaseName) {
        "Apple___Apple_scab" -> TreatmentInfo(
            plantName = "Apple",
            diseaseName = "Apple Scab",
            solutions = listOf(
                "Fungicides: Apply systemic and protectant fungicides according to recommended schedules and product labels. Multiple applications may be necessary.",
                "Pruning: Remove heavily infected twigs and leaves.",
                "Increase Airflow: Ensure good air circulation through pruning."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check trees weekly during the growing season for early signs of scab.",
                "Sanitation: Remove and destroy fallen leaves and fruit in autumn.",
                "Proper Pruning: Maintain an open canopy for better air circulation.",
                "Resistant Varieties: Consider planting scab-resistant apple varieties in new plantings."
            )
        )
        "Apple___healthy" -> TreatmentInfo(
            plantName = "Apple",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Proper Nutrition: Ensure the tree receives adequate nutrients through soil testing and appropriate fertilization.",
                "Adequate Watering: Provide consistent watering, especially during dry periods, but avoid overwatering.",
                "Regular Pruning: Prune annually to maintain shape, remove dead or damaged wood, and improve light penetration and air circulation.",
                "Pest and Disease Monitoring: Regularly inspect the tree for any signs of pests or diseases and address them promptly."
            )
        )
        "Cherry_(including_sour)___Powdery_mildew" -> TreatmentInfo(
            plantName = "Cherry",
            diseaseName = "Powdery Mildew",
            solutions = listOf(
                "Fungicides: Apply appropriate fungicides labeled for powdery mildew on cherries. Follow product instructions carefully.",
                "Pruning: Remove heavily infected shoots and leaves.",
                "Sulfur Sprays: Apply sulfur-based fungicides early in the season as a preventive measure."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check trees weekly for early signs of powdery mildew.",
                "Proper Spacing: Ensure adequate spacing between trees for good air circulation.",
                "Pruning: Maintain an open canopy through proper pruning.",
                "Resistant Varieties: Consider planting powdery mildew-resistant varieties in new plantings."
            )
        )
        "Cherry_(including_sour)___healthy" -> TreatmentInfo(
            plantName = "Cherry",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Proper Watering: Water deeply and regularly, especially during fruit development.",
                "Balanced Fertilization: Provide appropriate fertilization based on soil testing.",
                "Pruning: Prune to maintain an open canopy for good light penetration and air circulation. Remove any dead, damaged, or diseased wood.",
                "Pest and Disease Monitoring: Regularly inspect for any signs of pests or diseases."
            )
        )
        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot" -> TreatmentInfo(
            plantName = "Corn (Maize)",
            diseaseName = "Cercospora Leaf Spot / Gray Leaf Spot",
            solutions = listOf(
                "Fungicides: Apply appropriate fungicides labeled for Cercospora and Gray leaf spot at the recommended growth stages and under favorable weather conditions for disease development.",
                "Crop Rotation: Practice crop rotation to reduce disease pressure.",
                "Resistant Varieties: Plant disease-resistant corn varieties when available.",
                "Field Sanitation: Remove and destroy crop debris after harvest."
            ),
            maintenance = listOf(
                "Monitor Fields: Regularly scout fields for early signs of disease.",
                "Proper Spacing: Ensure adequate plant spacing for good air circulation.",
                "Fertility Management: Maintain balanced soil fertility.",
                "Irrigation Management: Avoid overhead irrigation if possible."
            )
        )
        "Corn_(maize)___Northern_Leaf_Blight" -> TreatmentInfo(
            plantName = "Corn (Maize)",
            diseaseName = "Northern Leaf Blight",
            solutions = listOf(
                "Fungicides: Apply fungicides effective against Northern Leaf Blight at the appropriate growth stages, particularly during early disease development.",
                "Crop Rotation: Implement a 2-3 year rotation with non-host crops.",
                "Resistant Varieties: Plant corn varieties with resistance to Northern Leaf Blight.",
                "Field Sanitation: Remove and destroy infected crop debris after harvest."
            ),
            maintenance = listOf(
                "Regular Scouting: Monitor fields weekly for early disease symptoms.",
                "Proper Plant Spacing: Ensure adequate spacing for good air circulation.",
                "Fertility Management: Maintain balanced soil fertility.",
                "Irrigation: Use drip irrigation instead of overhead irrigation when possible."
            )
        )
        "Corn_(maize)___healthy" -> TreatmentInfo(
            plantName = "Corn (Maize)",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Soil Health: Maintain healthy soil through proper tillage, organic matter management, and balanced fertilization.",
                "Adequate Water: Ensure sufficient water supply, especially during critical growth stages like pollination and grain fill.",
                "Weed Control: Implement effective weed management to reduce competition for resources.",
                "Pest Monitoring: Regularly scout for insect pests and manage them appropriately."
            )
        )
        "Grape___Esca_(Black_Measles)" -> TreatmentInfo(
            plantName = "Grape",
            diseaseName = "Esca (Black Measles)",
            solutions = listOf(
                "There is no effective cure for Esca once symptoms appear. Focus on preventing further spread.",
                "Remove and Destroy Infected Vines: Severely infected vines should be removed and burned.",
                "Improve Vineyard Management: Focus on practices that promote vine vigor and reduce stress.",
                "Fungicide Applications: Apply preventive fungicides during the growing season."
            ),
            maintenance = listOf(
                "Regular Monitoring: Inspect vines regularly for early symptoms.",
                "Proper Pruning: Use proper pruning techniques and avoid large pruning wounds.",
                "Vineyard Sanitation: Remove and destroy infected plant material.",
                "Stress Management: Implement practices that reduce vine stress, such as proper irrigation and nutrition."
            )
        )
        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)" -> TreatmentInfo(
            plantName = "Grape",
            diseaseName = "Leaf Blight (Isariopsis Leaf Spot)",
            solutions = listOf(
                "Fungicides: Apply appropriate fungicides labeled for Isariopsis Leaf Spot according to recommended schedules.",
                "Pruning: Remove and destroy infected leaves and canes.",
                "Improve Air Circulation: Prune to maintain an open canopy.",
                "Sanitation: Remove and destroy fallen leaves and debris."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check vines weekly for early symptoms.",
                "Proper Training: Train vines to maintain good air circulation.",
                "Fertility Management: Maintain balanced nutrition to promote healthy growth.",
                "Irrigation: Use drip irrigation instead of overhead irrigation when possible."
            )
        )
        "Grape___healthy" -> TreatmentInfo(
            plantName = "Grape",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Proper Training and Pruning: Train vines appropriately for the chosen system and prune annually to maintain shape, airflow, and fruit production.",
                "Balanced Fertilization: Provide nutrients based on soil testing and vine needs.",
                "Adequate Water Management: Ensure consistent water supply, especially during critical growth stages.",
                "Pest and Disease Monitoring: Regularly inspect vines for any signs of pests or diseases."
            )
        )
        "Peach___Bacterial_spot" -> TreatmentInfo(
            plantName = "Peach",
            diseaseName = "Bacterial Spot",
            solutions = listOf(
                "Copper-based Sprays: Continue copper applications during the growing season, following label restrictions and considering potential phytotoxicity.",
                "Pruning: Remove severely infected twigs.",
                "Antibiotic Sprays: Apply streptomycin or oxytetracycline sprays during bloom.",
                "Sanitation: Remove and destroy infected plant material."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check trees weekly for early symptoms.",
                "Proper Pruning: Maintain an open canopy for good air circulation.",
                "Irrigation: Use drip irrigation instead of overhead irrigation.",
                "Fertility Management: Avoid excessive nitrogen fertilization."
            )
        )
        "Peach___healthy" -> TreatmentInfo(
            plantName = "Peach",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Proper Pruning: Prune annually to maintain tree shape, remove dead or diseased wood, and improve light penetration.",
                "Balanced Fertilization: Provide appropriate nutrients based on soil testing.",
                "Adequate Watering: Water deeply and regularly, especially during fruit development.",
                "Pest and Disease Monitoring: Regularly inspect for any signs of pests or diseases."
            )
        )
        "Pepper,_bell___Bacterial_spot" -> TreatmentInfo(
            plantName = "Bell Pepper",
            diseaseName = "Bacterial Spot",
            solutions = listOf(
                "Copper-based Sprays: Apply copper-based fungicides, although their effectiveness may be limited once the disease is established.",
                "Remove Infected Plants: Promptly remove and destroy infected plants to prevent further spread.",
                "Antibiotic Sprays: Apply streptomycin or oxytetracycline sprays during early disease development.",
                "Sanitation: Remove and destroy infected plant material."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check plants weekly for early symptoms.",
                "Proper Spacing: Ensure adequate plant spacing for good air circulation.",
                "Irrigation: Use drip irrigation instead of overhead irrigation.",
                "Fertility Management: Avoid excessive nitrogen fertilization."
            )
        )
        "Pepper,_bell___healthy" -> TreatmentInfo(
            plantName = "Bell Pepper",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Well-drained Soil: Plant in well-drained soil to prevent root rot.",
                "Consistent Watering: Provide regular and consistent watering.",
                "Balanced Fertilization: Fertilize appropriately based on soil test recommendations.",
                "Weed Control: Manage weeds to reduce competition for resources and potential pest harborage."
            )
        )
        "Potato___Late_blight" -> TreatmentInfo(
            plantName = "Potato",
            diseaseName = "Late Blight",
            solutions = listOf(
                "Systemic Fungicides: Apply systemic fungicides labeled for late blight at recommended intervals.",
                "Hilling: Hill soil around the base of plants to protect tubers.",
                "Remove Infected Foliage: Remove and destroy infected leaves and stems.",
                "Sanitation: Remove and destroy infected plant material."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check plants weekly for early symptoms.",
                "Proper Spacing: Ensure adequate plant spacing for good air circulation.",
                "Irrigation: Use drip irrigation instead of overhead irrigation.",
                "Fertility Management: Maintain balanced soil fertility."
            )
        )
        "Potato___healthy" -> TreatmentInfo(
            plantName = "Potato",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Well-drained Soil: Plant in well-drained soil.",
                "Proper Hilling: Hill soil around the stems to support growth and protect tubers.",
                "Consistent Watering: Provide adequate and consistent watering, especially during tuber development.",
                "Pest and Disease Monitoring: Regularly inspect for pests and diseases."
            )
        )
        "Strawberry___Leaf_scorch" -> TreatmentInfo(
            plantName = "Strawberry",
            diseaseName = "Leaf Scorch",
            solutions = listOf(
                "Fungicides: Apply appropriate fungicides labeled for leaf scorch.",
                "Pruning: Remove and destroy infected leaves.",
                "Sanitation: Remove and destroy infected plant material.",
                "Improve Air Circulation: Maintain proper plant spacing."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check plants weekly for early symptoms.",
                "Proper Spacing: Ensure adequate plant spacing for good air circulation.",
                "Irrigation: Use drip irrigation instead of overhead irrigation.",
                "Fertility Management: Maintain balanced soil fertility."
            )
        )
        "Strawberry___healthy" -> TreatmentInfo(
            plantName = "Strawberry",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Proper Spacing: Plant strawberries with adequate spacing for air circulation.",
                "Weed Control: Manage weeds effectively.",
                "Consistent Watering: Provide regular watering, especially during fruiting.",
                "Mulching: Use mulch to conserve moisture, suppress weeds, and keep berries clean."
            )
        )
        "Tomato___Late_blight" -> TreatmentInfo(
            plantName = "Tomato",
            diseaseName = "Late Blight",
            solutions = listOf(
                "Systemic Fungicides: Apply systemic fungicides labeled for late blight at recommended intervals.",
                "Prune Infected Foliage: Remove and destroy infected leaves and stems immediately.",
                "Sanitation: Remove and destroy infected plant material.",
                "Improve Air Circulation: Maintain proper plant spacing."
            ),
            maintenance = listOf(
                "Regular Monitoring: Check plants weekly for early symptoms.",
                "Proper Spacing: Ensure adequate plant spacing for good air circulation.",
                "Irrigation: Use drip irrigation instead of overhead irrigation.",
                "Fertility Management: Maintain balanced soil fertility."
            )
        )
        "Tomato___healthy" -> TreatmentInfo(
            plantName = "Tomato",
            diseaseName = "Healthy",
            solutions = emptyList(),
            maintenance = listOf(
                "Well-drained Soil: Plant in well-drained soil.",
                "Consistent Watering: Water regularly and deeply.",
                "Staking or Caging: Provide support for plants to improve air circulation and keep fruit off the ground.",
                "Balanced Fertilization: Fertilize appropriately based on soil test recommendations."
            )
        )
        else -> TreatmentInfo(
            plantName = "Unknown",
            diseaseName = "Unknown",
            solutions = listOf("No specific treatment information available."),
            maintenance = listOf("No specific maintenance information available.")
        )
    }
}


