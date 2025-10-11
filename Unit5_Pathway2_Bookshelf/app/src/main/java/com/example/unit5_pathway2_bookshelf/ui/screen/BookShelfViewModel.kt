package com.example.unit5_pathway2_bookshelf.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.unit5_pathway2_bookshelf.BookShelfApplication
import com.example.unit5_pathway2_bookshelf.data.BookShelfRepository
import com.example.unit5_pathway2_bookshelf.network.Volume
import kotlinx.coroutines.launch
import okio.IOException

sealed interface BookShelfUiState {
    data class Success(val data: List<Volume>): BookShelfUiState

    object Error: BookShelfUiState
    object Loading: BookShelfUiState
}

class BookShelfViewModel(
    private val bookShelfRepository: BookShelfRepository
) : ViewModel() {
    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set

    init {
        getBookData()
    }

    fun getBookData() {
        viewModelScope.launch {
            bookShelfUiState = try {
                BookShelfUiState.Success(bookShelfRepository.getBookShelf())
            } catch (e: IOException) {
                BookShelfUiState.Error
            } catch (e: HttpException) {
                BookShelfUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val amphibiansRepository = application.container.bookShelfRepository
                BookShelfViewModel(
                    bookShelfRepository = amphibiansRepository
                )
            }
        }
    }
}