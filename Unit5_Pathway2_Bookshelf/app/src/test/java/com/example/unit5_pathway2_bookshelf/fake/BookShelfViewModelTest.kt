package com.example.marsphotos.fake

import com.example.marsphotos.rules.TestDispatcherRule
import com.example.unit5_pathway2_bookshelf.network.Volume
import com.example.unit5_pathway2_bookshelf.ui.screen.BookShelfUiState
import com.example.unit5_pathway2_bookshelf.ui.screen.BookShelfViewModel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookShelfViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @OptIn(InternalSerializationApi::class)
    @Test
    fun bookShelfViewModel_getBook_verifyMarsUiStateSuccess() =
        runTest {
            val bookShelfViewModel = BookShelfViewModel(
                bookShelfRepository = FakeNetworkBookShelfRepository()
            )
            val volumeList = mutableListOf<Volume>()
            volumeList.addAll(FakeDataSource.volumeList)
            volumeList.addAll(FakeDataSource.volumeList)
            assertEquals(
                BookShelfUiState.Success(volumeList.toList()),
                bookShelfViewModel.bookShelfUiState
            )
        }
}