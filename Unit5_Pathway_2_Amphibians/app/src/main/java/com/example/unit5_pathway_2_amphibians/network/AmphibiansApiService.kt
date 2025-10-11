package com.example.unit5_pathway_2_amphibians.network

import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibiansData(): List<AmphibiansData>
}