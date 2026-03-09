package com.weather.ui.weather.model

data class WeatherUiModel(
    val city: String,
    val coordinates: String,
    val temperature: String,
    val description: String,
    val iconRes: Int
)