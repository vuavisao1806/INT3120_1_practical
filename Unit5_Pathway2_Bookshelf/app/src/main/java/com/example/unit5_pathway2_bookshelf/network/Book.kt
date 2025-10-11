package com.example.unit5_pathway2_bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val title: String,
    val subtitle: String = "",
    val description: String = "",
    val publisher: String = "",
    val imageLinks: MutableMap<String, String>
)
