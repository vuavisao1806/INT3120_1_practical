package com.example.unit5_pathway2_bookshelf.network

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

@Serializable
data class ApiResponse(
    val items: List<Volume>
)

interface BookShelfApiService {

    @GET("volumes")
    suspend fun getBookShelfData(
        @Query(value = "q", encoded = true) query: String = QUERY,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 40,
        @Query("key") apiKey: String = KEY,
    ): ApiResponse

    companion object {
        const val QUERY = "subject:Computers"
        const val KEY = "AIzaSyAjmOAidXOZfSSxs1Vo6v6ARL5hCRUEo4g"
    }
}