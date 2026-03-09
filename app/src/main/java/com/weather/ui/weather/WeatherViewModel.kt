package com.weather.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.domain.usecase.GetWeatherRadomLocationUseCase
import com.weather.ui.weather.mapper.toUi
import com.weather.ui.weather.screenstate.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getRandomWeatherUseCase: GetWeatherRadomLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val state: StateFlow<WeatherUiState> = _state

    init {
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            getRandomWeatherUseCase()
                .map { weather ->
                    weather?.let {
                        WeatherUiState.Success(
                            weather.toUi()
                        )
                    } ?: WeatherUiState.Error("ERROR")
                }
                .onStart { emit(WeatherUiState.Loading) }
                .catch { emit(WeatherUiState.Error(it.message ?: "Unknown error")) }
                .collect { _state.value = it }
        }
    }
}