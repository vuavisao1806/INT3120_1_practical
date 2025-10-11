package com.example.unit5_pathway2_bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Volume(
    val id: String,
    @SerialName(value = "volumeInfo")
    val book: Book
)
