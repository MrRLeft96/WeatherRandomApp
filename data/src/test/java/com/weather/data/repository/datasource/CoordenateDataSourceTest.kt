package com.weather.data.repository.datasource

import com.weather.data.datasources.coordinates.CoordinateDataSource
import org.junit.Assert.*
import org.junit.Test

class CoordinatesDataSourceImplTest {

    private val dataSource = CoordinateDataSource()

    @Test
    fun `getRandomCoordinates returns valid latitude and longitude`() {
        val coordinates = dataSource.getRandomCoordinates()

        assertTrue(coordinates.first in -90.0..90.0)
        assertTrue(coordinates.second in -180.0..180.0)
    }

    @Test
    fun `getRandomCoordinates returns different values on multiple calls`() {
        val first = dataSource.getRandomCoordinates()
        val second = dataSource.getRandomCoordinates()

        // No es matemáticamente imposible que coincidan,
        // pero es extremadamente improbable.
        assertNotEquals(first, second)
    }
}