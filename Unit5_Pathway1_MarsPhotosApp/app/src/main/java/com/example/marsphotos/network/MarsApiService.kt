package com.example.marsphotos.network

import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET

interface MarsApiService {
    @OptIn(InternalSerializationApi::class)
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}