package com.weather.domain.repository

interface CoordinateRepository {

    /**
     * Devuelve una latitud y longitud aleatorias sobre tierra
     */
    fun getRandomCoordinates(): Pair<Double, Double>
}