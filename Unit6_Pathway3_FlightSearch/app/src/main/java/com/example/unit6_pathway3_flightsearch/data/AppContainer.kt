package com.example.unit6_pathway3_flightsearch.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val airportRepository: AirportRepository
    val flightRepository: FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        OfflineAirportRepository(AirportDatabase.Companion.getDatabse(context).airportDao())
    }

    override val flightRepository: FlightRepository by lazy {
        OfflineFlightRepository(FlightDatabase.Companion.getDatabse(context).flightDao())
    }
}
