package com.weather.domain.repository

import com.weather.domain.dto.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherByCoordinates(lat: Double, long: Double): Flow<Weather?>
}