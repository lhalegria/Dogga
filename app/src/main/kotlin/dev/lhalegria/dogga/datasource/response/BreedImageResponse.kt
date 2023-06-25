package dev.lhalegria.dogga.datasource.response

import com.google.gson.annotations.SerializedName

data class BreedImageResponse(
    @SerializedName("message") val imageUrl: String,
    @SerializedName("status") val status: String
)
