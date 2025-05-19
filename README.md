🌿 Plant Leaf Disease Detection App
An Android application that identifies plant leaf diseases using a deep learning model, helping farmers and gardeners detect issues early and take action.

📱 About the Project
This mobile app allows users to upload an image of a plant leaf and instantly get a disease prediction along with the disease name, description, sample images, and suggested treatments. It is built with Kotlin and Jetpack Compose, and the disease detection model is powered by TensorFlow Lite integrated directly into the app.

🧠 Model Details
Architecture: Hybrid CNN + MobileNet

Number of Classes: 20 plant leaf classes (healthy + diseased)

Trained On: Custom dataset with train/validation/test splits

Framework: TensorFlow

Deployment: Converted to .tflite and bundled inside the Android app

🛠 Tech Stack
Frontend: Kotlin, Jetpack Compose

ML Model: TensorFlow, MobileNet + custom CNN

Deployment: TensorFlow Lite, Android Studio

Dataset: 20 classes from multiple crops (e.g., Apple, Corn, Grape, Tomato)

🚀 Features
🌿 Upload a plant leaf image

🔍 Predict disease with high accuracy

📖 View detailed disease descriptions

🧪 See possible treatments and solutions

📊 Dataset info section (source, number of classes)

🧬 Disease info section with sample images
