package com.weather.domain.dto

data class Weather(
    val cityName: String,
    val country: String?,
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val description: String,
    val weatherType: WeatherType
)