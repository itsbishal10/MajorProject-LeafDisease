# NewLeaf - Plant Disease Detection App

NewLeaf is an Android application that helps users identify plant diseases using machine learning. The app uses TensorFlow Lite to analyze plant leaf images and provide accurate disease detection and treatment recommendations.

## Features

- **Plant Disease Detection**: Capture or upload images of plant leaves to detect diseases
- **Treatment Guide**: Get detailed treatment recommendations for detected diseases
- **Disease Information**: Access comprehensive information about various plant diseases
- **Dataset Information**: Learn about the training data and model accuracy
- **Dark/Light Mode**: Toggle between dark and light themes for comfortable viewing

## Technical Overview

### Architecture
- Built with Kotlin and Jetpack Compose
- Uses MVVM (Model-View-ViewModel) architecture pattern
- Implements Material 3 design system

### Key Components
1. **TFLiteModel**: Handles image processing and disease detection
2. **Screens**:
   - HomeScreen: Main dashboard
   - LeafDiseaseDetectionScreen: Disease detection interface
   - TreatmentScreen: Treatment recommendations
   - DiseaseInfoScreen: Disease information
   - DatasetInfoScreen: Dataset details

### Dependencies
- TensorFlow Lite for machine learning
- Jetpack Compose for UI
- Coil for image loading
- Navigation Compose for navigation
- Material 3 for design components

## Setup Instructions

1. **Prerequisites**
   - Android Studio Arctic Fox or newer
   - Android SDK 24 or higher
   - Kotlin 1.8.0 or higher

2. **Installation**
   ```bash
   # Clone the repository
   git clone [repository-url]
   
   # Open in Android Studio
   # Build and run the project
   ```

3. **Configuration**
   - Ensure you have the required TensorFlow Lite model file in the assets folder
   - Configure your development environment with the necessary SDK tools

## Usage Guide

### Disease Detection
1. Open the app
2. Navigate to the "Detect" tab
3. Choose to either:
   - Take a photo using the camera
   - Select an image from gallery
4. Wait for the analysis
5. View the results and treatment recommendations

### Viewing Disease Information
1. Go to the "Diseases" tab
2. Browse through the list of plant diseases
3. Select a disease to view detailed information

### Accessing Dataset Information
1. Navigate to the "Dataset" tab
2. View information about the training data
3. Check model accuracy and supported plant types

## Model Information

The app uses a TensorFlow Lite model trained on the PlantVillage dataset, which includes:
- Multiple plant species
- Various disease categories
- High-resolution leaf images

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

[Your License Information]

## Contact

[Your Contact Information]

## Acknowledgments

- PlantVillage dataset
- TensorFlow team
- Android development community
