package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusSchedulesRepository
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(
    private val busSchedulesRepository: BusSchedulesRepository
): ViewModel() {

    fun getFullSchedule(): Flow<List<BusSchedule>> = busSchedulesRepository.getAllBusSchedulesStream()

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> = busSchedulesRepository.getBusScheduleStream(stopName)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BusScheduleApplication)
                val busSchedulesRepository = application.container.busSchedulesRepository
                BusScheduleViewModel(
                    busSchedulesRepository = busSchedulesRepository
                )
            }
        }
    }
}
