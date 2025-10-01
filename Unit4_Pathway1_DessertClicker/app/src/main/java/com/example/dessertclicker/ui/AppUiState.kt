package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource.dessertList


data class AppUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[0].price,
    val currentDessertImageId: Int = dessertList[0].imageId
)