package com.weather.data.repository

import android.util.Log
import com.weather.data.datasources.remote.WeatherRemoteDataSource
import com.weather.data.datasources.remote.model.mapper.toDomain
import com.weather.domain.dto.Weather
import com.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getWeatherByCoordinates(lat: Double, long: Double): Flow<Weather?> = flow {
        try {
            val data = remoteDataSource.getWeatherByCoordinates(lat, long)
            emit(data.toDomain())
        } catch (e: Exception) {
            Log.e("SERVER ERROR", e.message.toString())
            emit(null)
        }
    }
}