package com.weather.domain.usecase

import com.weather.domain.dto.Weather
import com.weather.domain.repository.CoordinateRepository
import com.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherRadomLocationUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val coordinateRepository: CoordinateRepository
) {
    suspend operator fun invoke(): Flow<Weather?> {
        val (lat, lon) = coordinateRepository.getRandomCoordinates()
        return weatherRepository.getWeatherByCoordinates(lat, lon)
    }
}