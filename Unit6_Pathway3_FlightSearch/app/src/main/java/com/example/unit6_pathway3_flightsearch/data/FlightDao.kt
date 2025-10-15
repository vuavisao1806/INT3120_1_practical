package com.example.unit6_pathway3_flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertFlight(flight: Flight)

    @Delete
    suspend fun deleteFlight(flight: Flight)

    @Query(
        """
            SELECT * FROM favorite 
            WHERE departure_code = :code
        """
    )
    fun getFLightByDepCode(code: String): Flow<List<Flight>>

    @Query(
        """
            SELECT * FROM favorite 
            WHERE departure_code = :depCode AND destination_code = :desCode
        """
    )
    fun isFavoriteFlight(depCode: String, desCode: String): Flow<Flight?>

    @Query(
        """
            SELECT * FROM favorite
        """
    )
    fun getAllFlights(): Flow<List<Flight>>
}