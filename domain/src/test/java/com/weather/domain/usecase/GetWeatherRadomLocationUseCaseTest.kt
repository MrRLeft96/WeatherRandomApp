package com.weather.domain.usecase
import com.weather.domain.dto.Weather
import com.weather.domain.dto.WeatherType
import com.weather.domain.repository.CoordinateRepository
import com.weather.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetWeatherRadomLocationUseCaseTest {

    private lateinit var coordinateRepository: CoordinateRepository
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var useCase: GetWeatherRadomLocationUseCase

    @Before
    fun setup() {
        coordinateRepository = mockk()
        weatherRepository = mockk()
        useCase = GetWeatherRadomLocationUseCase(
            weatherRepository,
            coordinateRepository
        )
    }

    @Test
    fun `invoke gets coordinates and fetches weather`() = runTest {

        // Given
        val coordinates = Pair(40.4168, -3.7038)

        val expectedWeather = Weather(
            cityName = "Madrid",
            latitude = 40.4168,
            longitude = -3.7038,
            temperature = 20.0,
            description = "Soleado",
            weatherType = WeatherType.CLEAR,
            country = "España"
        )

        every { coordinateRepository.getRandomCoordinates() } returns coordinates

        coEvery {
            weatherRepository.getWeatherByCoordinates(
                lat = 40.4168,
                long = -3.7038
            )
        } returns flowOf(expectedWeather)

        // When
        val result = useCase().first()

        // Then
        assertEquals(expectedWeather, result)
    }
}