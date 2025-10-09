package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto
import kotlinx.serialization.InternalSerializationApi

interface MarsPhotosRepository {
    @OptIn(InternalSerializationApi::class)
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    @OptIn(InternalSerializationApi::class)
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()

}