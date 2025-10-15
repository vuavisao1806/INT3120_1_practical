package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusSchedulesRepository(private val scheduleDao: ScheduleDao) : BusSchedulesRepository {
    override fun getAllBusSchedulesStream(): Flow<List<BusSchedule>> = scheduleDao.getAllBusSchedules()

    override fun getBusScheduleStream(stopName: String): Flow<List<BusSchedule>> = scheduleDao.getBusSchedule(stopName)

    override suspend fun insertBusSchedule(busSchedule: BusSchedule) = scheduleDao.insert(busSchedule)

    override suspend fun deleteBusSchedule(busSchedule: BusSchedule) = scheduleDao.delete(busSchedule)

    override suspend fun updateBusSchedule(busSchedule: BusSchedule) = scheduleDao.update(busSchedule)
}