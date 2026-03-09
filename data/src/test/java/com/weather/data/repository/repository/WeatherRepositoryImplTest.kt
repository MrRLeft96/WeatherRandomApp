package com.weather.data.repository.repository

import android.util.Log
import com.weather.data.datasources.remote.WeatherRemoteDataSource
import com.weather.data.datasources.remote.model.dto.CoordDto
import com.weather.data.datasources.remote.model.dto.MainDto
import com.weather.data.datasources.remote.model.dto.SysDto
import com.weather.data.datasources.remote.model.dto.WeatherDto
import com.weather.data.datasources.remote.model.dto.WeatherResponseDto
import com.weather.data.repository.WeatherRepositoryImpl
import com.weather.domain.dto.Weather
import com.weather.domain.dto.WeatherType
import com.weather.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private lateinit var remoteDataSource: WeatherRemoteDataSource
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        remoteDataSource = mockk()
        repository = WeatherRepositoryImpl(remoteDataSource)
        mockkStatic("android.util.Log")

        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    @Test
    fun `getWeather emits Loading then Success for random coordinates`() = runTest {
        // Arrange: generamos coordenadas random
        val lat = Random.Default.nextDouble(-90.0, 90.0)
        val lon = Random.Default.nextDouble(-180.0, 180.0)

        val fakeDto = WeatherResponseDto(
            coord = CoordDto(
                lon = lon,
                lat = lat
            ),
            weather = listOf(
                WeatherDto(
                    main = "Clear",
                    description = "clear sky"
                )
            ),
            main = MainDto(
                temp = 22.5,
                feels_like = 21.8,
                humidity = 60,
                pressure = 1013
            ),
            name = "Madrid",
            sys = SysDto(
                country = "ES"
            )
        )
        coEvery { remoteDataSource.getWeatherByCoordinates(lat, lon) } returns fakeDto

        // Act
        val emissions = repository.getWeatherByCoordinates(lat, lon).toList()

        // Assert
        val success = emissions[0] as Weather
        Assert.assertEquals(lat, success.latitude, 0.001)
        Assert.assertEquals(lon, success.longitude, 0.001)
        Assert.assertEquals(WeatherType.CLEAR, success.weatherType)
    }

    @Test
    fun `getWeather emits Loading then Error for random coordinates`() = runTest {
        val lat = Random.Default.nextDouble(-90.0, 90.0)
        val lon = Random.Default.nextDouble(-180.0, 180.0)

        coEvery {
            remoteDataSource.getWeatherByCoordinates(
                lat,
                lon
            )
        } throws RuntimeException("Network failed")

        val emissions = repository.getWeatherByCoordinates(lat, lon).toList()

        Assert.assertNull(emissions[0])
    }
}