package dev.lhalegria.dogga.datasource.service

import dev.lhalegria.dogga.datasource.response.BreedImageResponse
import dev.lhalegria.dogga.datasource.response.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("breeds/list")
    suspend fun getBreeds(): Response<BreedsResponse>

    @GET("breed/{breed}/list")
    suspend fun getSubBreedsFromBreed(
        @Path("breed") breed: String
    ): Response<BreedsResponse>

    @GET("breed/{breed}/images/random")
    suspend fun getBreedImage(
        @Path("breed") breed: String
    ): Response<BreedImageResponse>
}
