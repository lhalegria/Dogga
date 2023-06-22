package dev.lhalegria.dogga.datasource.response

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
    @SerializedName("message") val breedsList: List<String>,
    @SerializedName("status") val status: String
)
