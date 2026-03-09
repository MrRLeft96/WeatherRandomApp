package com.weather.data.datasources.remote.model.mapper

import com.weather.data.datasources.remote.model.dto.WeatherResponseDto
import com.weather.domain.dto.Weather
import com.weather.domain.dto.WeatherType

fun WeatherResponseDto.toDomain(): Weather {
    val weatherInfo = weather.firstOrNull()

    return Weather(
        cityName = name,
        latitude = coord.lat,
        longitude = coord.lon,
        temperature = main.temp,
        description = weatherInfo?.description.orEmpty(),
        weatherType = weatherInfo?.main?.toWeatherType() ?: WeatherType.UNKNOWN,
        country = sys.country
    )
}

fun String?.toWeatherType(): WeatherType {
    return when (this?.lowercase()) {
        "clear" -> WeatherType.CLEAR
        "clouds" -> WeatherType.CLOUDS
        "rain" -> WeatherType.RAIN
        "drizzle" -> WeatherType.DRIZZLE
        "thunderstorm" -> WeatherType.THUNDERSTORM
        "snow" -> WeatherType.SNOW
        "mist" -> WeatherType.MIST
        "fog" -> WeatherType.FOG
        else -> WeatherType.UNKNOWN
    }
}