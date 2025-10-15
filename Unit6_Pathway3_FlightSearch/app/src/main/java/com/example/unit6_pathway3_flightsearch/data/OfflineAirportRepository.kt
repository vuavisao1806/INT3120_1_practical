package com.example.unit6_pathway3_flightsearch.data

import kotlinx.coroutines.flow.Flow

class OfflineAirportRepository(private val airportDao: AirportDao) : AirportRepository {

    override fun getAirportSuggestion(text: String): Flow<List<Airport>> = airportDao.getAirportSuggestion(text)
    override fun getAllAirportExcept(code: String): Flow<List<Airport>> = airportDao.getAllAirportExcept(code)
    override fun getAirportByCode(code: String): Flow<Airport> = airportDao.getAirportByCode(code)
}