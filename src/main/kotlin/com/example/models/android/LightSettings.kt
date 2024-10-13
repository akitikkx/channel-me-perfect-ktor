package com.example.models.android

data class LightSettings(
    val color: Color,
    val lightOnDuration: String,
    val lightOffDuration: String
)

data class Color(
    val red: Float,
    val green: Float,
    val blue: Float,
    val alpha: Float? = null
)