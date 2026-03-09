package com.weather.data.datasources.coordinates

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class CoordinateDataSource @Inject constructor() {

    /**
     * Devuelve una latitud y longitud aleatorias sobre tierra
     */
    fun getRandomCoordinates(): Pair<Double, Double> {
        val region = landRegions.random()
        val lat = Random.nextDouble(region.minLat, region.maxLat)
        val lon = Random.nextDouble(region.minLon, region.maxLon)
        return lat to lon
    }

    /**
     * Rectángulos aproximados de zonas terrestres
     */
    private val landRegions = listOf(
        LandRegion(24.396308, 49.384358, -125.0, -66.93457),   // USA continental
        LandRegion(35.0, 71.0, -10.0, 40.0),                  // Europa
        LandRegion(-35.0, 35.0, -20.0, 55.0),                 // África
        LandRegion(5.0, 55.0, 60.0, 150.0),                   // Asia
        LandRegion(-55.0, 12.0, -82.0, -34.0),                // América Central y Sudamérica
        LandRegion(-50.0, -10.0, 110.0, 155.0),               // Australia
        LandRegion(-50.0, -10.0, -80.0, -30.0)                // Sudamérica sur
    )

    private data class LandRegion(
        val minLat: Double,
        val maxLat: Double,
        val minLon: Double,
        val maxLon: Double
    )
}