package com.example.marsphotos.fake

import com.example.unit5_pathway2_bookshelf.network.ApiResponse
import com.example.unit5_pathway2_bookshelf.network.Book
import com.example.unit5_pathway2_bookshelf.network.Volume

object FakeDataSource {

    const val idOne = "img1"
    const val idTwo = "img2"
    val volumeList = listOf(
        Volume(
            id = idOne,
            book = Book(
                title = idOne,
                imageLinks = mutableMapOf("thumbnail" to "")
            )
        ),
        Volume(
            id = idTwo,
            book = Book(
                title = idTwo,
                imageLinks = mutableMapOf("thumbnail" to "")
            )
        )
    )

    val apiResponse = ApiResponse(items = volumeList)
}