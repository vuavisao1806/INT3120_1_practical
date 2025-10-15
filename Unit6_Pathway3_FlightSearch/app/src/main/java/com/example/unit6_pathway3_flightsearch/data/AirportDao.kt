package com.example.unit6_pathway3_flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query(
        """
            SELECT * FROM airport 
            WHERE iata_code LIKE '%' || :text || '%' OR name LIKE '%' || :text || '%'
            ORDER BY passengers DESC
        """
    )
    fun getAirportSuggestion(text: String): Flow<List<Airport>>

    @Query(
        """
            SELECT * FROM airport 
            WHERE iata_code != :code
            ORDER BY passengers DESC
        """
    )
    fun getAllAirportExcept(code: String): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code = :code")
    fun getAirportByCode(code: String): Flow<Airport>
}