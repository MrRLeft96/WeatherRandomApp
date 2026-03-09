package com.weather.data.datasources.remote

import com.weather.data.datasources.remote.model.dto.WeatherResponseDto
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
    private val api: WeatherApiService
) {
    suspend fun getWeatherByCoordinates(lat: Double, lon: Double): WeatherResponseDto {
        return api.getWeatherByCoordinates(lat, lon)
    }
}