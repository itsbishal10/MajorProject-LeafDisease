package com.example.newleaf.data

data class User(
    val id: String = "",
    val name: String = ""
)

data class Detection(
    val id: String = "",
    val userId: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val diseaseName: String = "",
    val confidence: Float = 0f,
    val imageUrl: String = ""
) 