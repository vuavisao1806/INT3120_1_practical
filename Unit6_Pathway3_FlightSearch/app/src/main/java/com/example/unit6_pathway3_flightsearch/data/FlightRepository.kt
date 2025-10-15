package com.example.unit6_pathway3_flightsearch.data

import kotlinx.coroutines.flow.Flow

interface FlightRepository {
    fun isFavoriteFlight(depCode: String, desCode: String): Flow<Int>

    fun getFLightByDepCode(code: String): Flow<List<Flight>>

    suspend fun insertFlight(flight: Flight)

    suspend fun deleteFlight(flight: Flight)

    fun getAllFlights(): Flow<List<Flight>>
}