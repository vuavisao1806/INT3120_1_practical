package com.example.marsphotos.fake

import com.example.unit5_pathway2_bookshelf.data.NetworkBookShelfRepository
import com.example.unit5_pathway2_bookshelf.network.Volume
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsRepositoryTest {
    @OptIn(InternalSerializationApi::class)
    @Test
    fun networkBookShelfRepository_getVolume_verifyVolumeList() =
        runTest {
            val repository = NetworkBookShelfRepository(
                bookShelfApiService = FakeBookApiService()
            )
            val volumeList = mutableListOf<Volume>()
            volumeList.addAll(FakeDataSource.volumeList)
            volumeList.addAll(FakeDataSource.volumeList)
            assertEquals(volumeList.toList(), repository.getBookShelf())
        }
}