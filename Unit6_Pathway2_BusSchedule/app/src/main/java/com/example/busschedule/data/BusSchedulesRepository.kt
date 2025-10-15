package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [BusSchedule] from a given data source.
 */
interface BusSchedulesRepository {
    /**
     * Retrieve all the busSchedules from the the given data source.
     */
    fun getAllBusSchedulesStream(): Flow<List<BusSchedule>>

    /**
     * Retrieve an busSchedule from the given data source that matches with the [stopName].
     */
    fun getBusScheduleStream(stopName: String): Flow<List<BusSchedule>>

    /**
     * Insert busSchedule in the data source
     */
    suspend fun insertBusSchedule(busSchedule: BusSchedule)

    /**
     * Delete busSchedule from the data source
     */
    suspend fun deleteBusSchedule(busSchedule: BusSchedule)

    /**
     * Update busSchedule in the data source
     */
    suspend fun updateBusSchedule(busSchedule: BusSchedule)
}