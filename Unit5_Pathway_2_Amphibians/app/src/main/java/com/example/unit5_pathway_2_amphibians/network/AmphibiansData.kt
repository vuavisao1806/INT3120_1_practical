package com.example.unit5_pathway_2_amphibians.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansData(
    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src")
    val imageSrc: String
)
