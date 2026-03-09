package com.weather.ui.weather.mapper


import com.weather.R
import com.weather.domain.dto.Weather
import com.weather.domain.dto.WeatherType
import com.weather.ui.weather.model.WeatherUiModel

fun WeatherType.toIconRes(): Int {
    return when (this) {
        WeatherType.CLEAR -> R.raw.ic_clear
        WeatherType.CLOUDS -> R.raw.ic_cloudy
        WeatherType.RAIN -> R.raw.ic_rain
        WeatherType.DRIZZLE -> R.raw.ic_drizzle
        WeatherType.THUNDERSTORM -> R.raw.ic_thunderstorms
        WeatherType.SNOW -> R.raw.ic_snow
        WeatherType.MIST -> R.raw.ic_mist
        WeatherType.FOG -> R.raw.ic_fog
        WeatherType.UNKNOWN -> R.raw.ic_not_available
    }
}

fun Weather.toUi(): WeatherUiModel {
    return WeatherUiModel(
        city = country?.let {
            "$cityName, $country"
        } ?: cityName,
        coordinates = "Lat: $latitude, Lon: $longitude",
        temperature = "${temperature.toInt()}°C",
        description = description.replaceFirstChar { it.uppercase() },
        iconRes = weatherType.toIconRes()
    )
}