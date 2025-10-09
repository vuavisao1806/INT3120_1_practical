package com.example.marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.network.MarsPhoto
import kotlinx.serialization.InternalSerializationApi

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    @OptIn(InternalSerializationApi::class)
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}