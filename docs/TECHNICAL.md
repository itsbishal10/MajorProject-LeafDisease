# NewLeaf Technical Documentation

## Project Structure

```
app/src/main/java/com/example/newleaf/
├── MainActivity.kt              # Main application entry point
├── TFLiteModel.kt              # TensorFlow Lite model implementation
├── data/                       # Data layer
├── ui/                         # UI components
│   └── screens/               # Screen composables
│       ├── HomeScreen.kt
│       ├── LeafDiseaseDetectionScreen.kt
│       ├── TreatmentScreen.kt
│       ├── DiseaseInfoScreen.kt
│       └── DatasetInfoScreen.kt
└── theme/                      # UI theme definitions
```

## Core Components

### 1. TFLiteModel.kt
Handles all machine learning operations:
- Image preprocessing
- Model inference
- Result interpretation

Key methods:
```kotlin
class TFLiteModel(context: Context) {
    fun classifyImage(bitmap: Bitmap): Prediction
    fun preprocessImage(bitmap: Bitmap): ByteBuffer
    fun close()
}
```

### 2. MainActivity.kt
Main application component that:
- Initializes the TFLite model
- Sets up navigation
- Manages theme state
- Handles image capture/selection

### 3. Screen Components

#### HomeScreen.kt
- Main dashboard
- Quick access to features
- App overview

#### LeafDiseaseDetectionScreen.kt
- Image capture/selection
- Disease detection interface
- Results display

#### TreatmentScreen.kt
- Treatment recommendations
- Disease-specific solutions
- Prevention tips

#### DiseaseInfoScreen.kt
- Disease catalog
- Detailed information
- Sample images

#### DatasetInfoScreen.kt
- Dataset statistics
- Model information
- Accuracy metrics

## Data Flow

1. **Image Input**
   - User captures/selects image
   - Image is processed to required format
   - Preprocessed image is passed to model

2. **Model Inference**
   - TFLite model processes image
   - Returns prediction results
   - Results are formatted for display

3. **Result Display**
   - Prediction is shown to user
   - Treatment options are displayed
   - Additional information is provided

## Dependencies

```gradle
dependencies {
    // TensorFlow Lite
    implementation("org.tensorflow:tensorflow-lite:2.14.0")
    implementation("org.tensorflow:tensorflow-lite-support:0.1.0")
    
    // UI Components
    implementation("androidx.compose.material3:material3:1.0.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    
    // Image Loading
    implementation("io.coil-kt:coil-compose:2.4.0")
}
```

## Model Specifications

### Input
- Image size: 224x224 pixels
- Color format: RGB
- Normalization: [0,1] range

### Output
- Disease classification
- Confidence score
- Plant type identification

## Performance Considerations

1. **Memory Management**
   - Model is loaded once at startup
   - Images are processed in background
   - Resources are properly released

2. **Image Processing**
   - Efficient bitmap handling
   - Proper image scaling
   - Memory-efficient transformations

3. **UI Performance**
   - Lazy loading of images
   - Efficient state management
   - Smooth navigation transitions

## Testing

### Unit Tests
- Model inference tests
- Image processing tests
- UI component tests

### Integration Tests
- End-to-end flow tests
- Navigation tests
- State management tests

## Future Improvements

1. **Model Enhancements**
   - Support for more plant types
   - Improved accuracy
   - Faster inference

2. **Feature Additions**
   - Offline mode
   - Batch processing
   - History tracking

3. **UI Improvements**
   - AR features
   - Enhanced visualization
   - Better accessibility

## Troubleshooting

### Common Issues

1. **Model Loading Failures**
   - Check model file presence
   - Verify model version
   - Ensure proper initialization

2. **Image Processing Errors**
   - Verify image format
   - Check memory usage
   - Validate preprocessing steps

3. **UI Issues**
   - Check Compose version
   - Verify theme implementation
   - Validate navigation setup

## Contributing Guidelines

1. **Code Style**
   - Follow Kotlin style guide
   - Use proper documentation
   - Maintain consistent formatting

2. **Testing Requirements**
   - Write unit tests
   - Include integration tests
   - Document test cases

3. **Pull Request Process**
   - Create feature branch
   - Update documentation
   - Submit PR with description 