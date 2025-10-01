package com.example.dessertclicker.ui;

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()


    private fun updateAppState() {

    }

    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow(
        desserts: List<Dessert> = dessertList,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun updateUserClick() {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue.plus(currentState.currentDessertPrice),
                dessertsSold = currentState.dessertsSold.inc(),

            )
        }
        val dessertToShow = determineDessertToShow(dessertsSold = _uiState.value.dessertsSold)

        _uiState.update { currentState ->
            currentState.copy(
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }
}
