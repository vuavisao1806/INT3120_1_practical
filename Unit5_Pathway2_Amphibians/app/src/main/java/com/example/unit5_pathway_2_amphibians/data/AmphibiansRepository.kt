package com.example.unit5_pathway_2_amphibians.data

import com.example.unit5_pathway_2_amphibians.network.AmphibiansApiService
import com.example.unit5_pathway_2_amphibians.network.AmphibiansData

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansData>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansData> = amphibiansApiService.getAmphibiansData()
}