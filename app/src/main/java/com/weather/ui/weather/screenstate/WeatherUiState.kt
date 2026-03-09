package com.weather.ui.weather.screenstate

import com.weather.ui.weather.model.WeatherUiModel

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(
        val weather: WeatherUiModel
    ) : WeatherUiState()

    data class Error(val message: String) : WeatherUiState()
}