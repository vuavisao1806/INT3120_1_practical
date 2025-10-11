package com.example.unit5_pathway2_bookshelf.data

import android.util.Log
import com.example.unit5_pathway2_bookshelf.network.BookShelfApiService
import com.example.unit5_pathway2_bookshelf.network.Volume
import kotlinx.coroutines.coroutineScope

interface BookShelfRepository {
    suspend fun getBookShelf(): List<Volume>
}

class NetworkBookShelfRepository(
    private val bookShelfApiService: BookShelfApiService
) : BookShelfRepository {
    override suspend fun getBookShelf(): List<Volume> {
        val data: MutableList<Volume> = mutableListOf()
//        val data = bookShelfApiService.getBookShelfData().items
        for (i in 0..1) {
            coroutineScope {
                data.addAll(bookShelfApiService.getBookShelfData(
                    startIndex = i * 40,
                ).items)
            }
        }

        data.forEach { volume ->
            val oldLink = volume.book.imageLinks["thumbnail"]
            volume.book.imageLinks["thumbnail"] = oldLink!!.replace("http", "https")
        }
//        Log.i("size of data: ", data.size.toString())
        return data.toList()
    }
}