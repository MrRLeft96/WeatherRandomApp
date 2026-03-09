package com.weather.data.repository

import com.weather.data.datasources.coordinates.CoordinateDataSource
import com.weather.domain.repository.CoordinateRepository
import javax.inject.Inject

class CoordinateRepositoryImpl @Inject constructor(private val coordinateDataSource: CoordinateDataSource) :
    CoordinateRepository {
    override fun getRandomCoordinates(): Pair<Double, Double> =
        coordinateDataSource.getRandomCoordinates()

}