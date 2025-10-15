package com.example.busschedule.data

import android.content.Context
import kotlin.getValue

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val busSchedulesRepository: BusSchedulesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineBusSchedulesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [BusSchedulesRepository]
     */
    override val busSchedulesRepository: BusSchedulesRepository by lazy {
        OfflineBusSchedulesRepository(ScheduleDatabase.getDatabse(context).scheduleDao())
    }
}
