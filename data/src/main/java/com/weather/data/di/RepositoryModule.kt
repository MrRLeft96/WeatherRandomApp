package com.weather.data.di

import com.weather.data.repository.CoordinateRepositoryImpl
import com.weather.data.repository.WeatherRepositoryImpl
import com.weather.domain.repository.CoordinateRepository
import com.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(
        impl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    abstract fun bindCoordinateRepository(
        impl: CoordinateRepositoryImpl
    ): CoordinateRepository
}