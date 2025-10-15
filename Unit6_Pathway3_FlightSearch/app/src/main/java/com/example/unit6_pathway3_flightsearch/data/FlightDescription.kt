package com.example.unit6_pathway3_flightsearch.data

data class FlightDescription(
    val id: Int,
    val departure: Airport,
    val destination: Airport,
    val isFavorite: Boolean
)
