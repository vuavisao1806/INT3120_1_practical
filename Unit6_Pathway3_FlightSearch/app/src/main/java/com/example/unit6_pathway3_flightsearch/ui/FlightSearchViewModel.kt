package com.example.unit6_pathway3_flightsearch.ui

import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.unit6_pathway3_flightsearch.FlightSearchApplication
import com.example.unit6_pathway3_flightsearch.data.Airport
import com.example.unit6_pathway3_flightsearch.data.AirportRepository
import com.example.unit6_pathway3_flightsearch.data.Flight
import com.example.unit6_pathway3_flightsearch.data.FlightDescription
import com.example.unit6_pathway3_flightsearch.data.FlightRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val airportRepository: AirportRepository,
    private val flightRepository: FlightRepository
): ViewModel() {
    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search

    private val _isTyping = MutableStateFlow(false)
    val isTyping: StateFlow<Boolean> = _isTyping

    private val _favoriteChanged = MutableStateFlow(false)

    private val _selectedDepAirport = MutableStateFlow<Airport?>(null)
    val selectedDepAirport: StateFlow<Airport?> = _selectedDepAirport

    @OptIn(ExperimentalCoroutinesApi::class)
    val flightList: StateFlow<List<FlightDescription>> =
        selectedDepAirport
            .filterNotNull()
            .flatMapLatest { depAirport ->
                _favoriteChanged.flatMapLatest {
                    airportRepository
                        .getAllAirportExcept(depAirport.iataCode)
                        .map { airportList ->
                            airportList.map { desAirport ->
                                val id = flightRepository
                                    .isFavoriteFlight(depAirport.iataCode, desAirport.iataCode)
                                    .first()
                                FlightDescription(
                                    id = id,
                                    departure = depAirport,
                                    destination = desAirport,
                                    isFavorite = (id != 0)
                                )
                            }
                        }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    val favoriteFlights: StateFlow<List<FlightDescription>> =
        flightRepository
            .getAllFlights()
            .map { flightList ->
                flightList.map { flight ->
                    val departure = airportRepository.getAirportByCode(flight.departureCode).first()
                    val destination = airportRepository.getAirportByCode(flight.destinationCode).first()

                    FlightDescription(
                        id = flight.id,
                        departure = departure,
                        destination = destination,
                        isFavorite = true
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    fun onTyping(text: String) {
        _search.value = text
        _isTyping.value = true
    }

    fun onChoosingAirport(airport: Airport) {
        _selectedDepAirport.value = airport
        _isTyping.value = false
    }

    fun changeFavoriteFlight(flightDescription: FlightDescription) {
        viewModelScope.launch {
            if (flightDescription.isFavorite) {
                flightRepository.deleteFlight(flightDescription.toFlight())
            } else {
                flightRepository.insertFlight(flightDescription.toFlight())
            }
            _favoriteChanged.value = !_favoriteChanged.value
        }
    }

//    fun getFavoriteFlights(): Flow<List<FlightDescription>> = flightRepository
//        .getAllFlights()
//        .map { flightList ->
//            flightList.map { flight ->
//                val departure = airportRepository.getAirportByCode(flight.departureCode).first()
//                val destination = airportRepository.getAirportByCode(flight.destinationCode).first()
//
//                FlightDescription(
//                    id = flight.id,
//                    departure = departure,
//                    destination = destination,
//                    isFavorite = true
//                )
//            }
//        }


    fun getSuggestionAirports(text: String): Flow<List<Airport>> = airportRepository.getAirportSuggestion(text)

//    fun getFlightList(depAirport: Airport): Flow<List<FlightDescription>> = airportRepository
//        .getAllAirportExcept(depAirport.iataCode)
//        .map { airportList ->
//            airportList.map { desAirport ->
//                val id = flightRepository.isFavoriteFlight(depAirport.iataCode, desAirport.iataCode).first()
//                FlightDescription(
//                    id = id,
//                    departure = depAirport,
//                    destination = desAirport,
//                    isFavorite = (id != 0)
//                )
//            }
//        }

    suspend fun insertFlight(flightDescription: FlightDescription) = flightRepository.insertFlight(flightDescription.toFlight())
    suspend fun deleteFlight(flightDescription: FlightDescription) = flightRepository.deleteFlight(flightDescription.toFlight())

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                FlightSearchViewModel(
                    airportRepository = application.container.airportRepository,
                    flightRepository = application.container.flightRepository
                )
            }
        }
    }
}

fun FlightDescription.toFlight(): Flight {
    return Flight(
        id = id,
        departureCode = departure.iataCode,
        destinationCode = destination.iataCode
    )
}