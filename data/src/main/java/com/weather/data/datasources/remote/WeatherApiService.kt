package com.weather.data.datasources.remote

import com.weather.data.BuildConfig
import com.weather.data.datasources.remote.model.dto.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es",
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_APP_ID
    ): WeatherResponseDto
}