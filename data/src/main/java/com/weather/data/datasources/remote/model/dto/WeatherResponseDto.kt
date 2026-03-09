package com.weather.data.datasources.remote.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponseDto(
    val coord: CoordDto,
    val weather: List<WeatherDto>,
    val main: MainDto,
    val name: String,
    val sys: SysDto
)

data class SysDto(
    val country: String?
)

@JsonClass(generateAdapter = true)
data class CoordDto(
    val lon: Double,
    val lat: Double
)

@JsonClass(generateAdapter = true)
data class MainDto(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int
)

@JsonClass(generateAdapter = true)
data class WeatherDto(
    val main: String,
    val description: String
)