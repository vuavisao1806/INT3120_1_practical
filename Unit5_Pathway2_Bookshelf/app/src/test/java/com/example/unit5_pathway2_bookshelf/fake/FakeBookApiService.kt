package com.example.marsphotos.fake

import com.example.unit5_pathway2_bookshelf.network.ApiResponse
import com.example.unit5_pathway2_bookshelf.network.BookShelfApiService
import kotlinx.serialization.InternalSerializationApi

class FakeBookApiService : BookShelfApiService {
    override suspend fun getBookShelfData(
        query: String,
        startIndex: Int,
        maxResults: Int,
        apiKey: String
    ): ApiResponse {
        return FakeDataSource.apiResponse
    }
}