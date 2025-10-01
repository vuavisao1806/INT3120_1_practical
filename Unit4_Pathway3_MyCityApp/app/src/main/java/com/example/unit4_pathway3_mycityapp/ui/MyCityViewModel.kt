package com.example.unit4_pathway3_mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.unit4_pathway3_mycityapp.model.DataSource.cafes
import com.example.unit4_pathway3_mycityapp.model.DataSource.restaurants
import com.example.unit4_pathway3_mycityapp.model.DataSource.shoppingCenters
import com.example.unit4_pathway3_mycityapp.model.Item
import com.example.unit4_pathway3_mycityapp.model.MyCityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState: StateFlow<MyCityUiState> = _uiState.asStateFlow()

    fun chooseBaseOnCategory(): List<Item> {
        val item = uiState.value.currentItem
        val list = when (item!!.id) {
            1 -> cafes
            2 -> restaurants
            else -> shoppingCenters
        }
        return list
    }

    fun updateItem(item: Item) {
        _uiState.update { currentState ->
            currentState.copy(
                currentItem = item
            )
        }
    }
}