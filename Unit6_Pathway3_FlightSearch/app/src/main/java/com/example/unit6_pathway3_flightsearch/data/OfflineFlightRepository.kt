package com.example.unit6_pathway3_flightsearch.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFlightRepository(private val flightDao: FlightDao) : FlightRepository {
    override fun isFavoriteFlight(
        depCode: String,
        desCode: String
    ): Flow<Int> = flightDao.isFavoriteFlight(depCode, desCode).map { flight -> flight?.id ?: 0 }

    override fun getFLightByDepCode(code: String): Flow<List<Flight>> = flightDao.getFLightByDepCode(code)

    override suspend fun insertFlight(flight: Flight) = flightDao.insertFlight(flight)

    override suspend fun deleteFlight(flight: Flight) = flightDao.deleteFlight(flight)

    override fun getAllFlights(): Flow<List<Flight>> = flightDao.getAllFlights()
}