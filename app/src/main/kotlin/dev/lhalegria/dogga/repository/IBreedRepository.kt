package dev.lhalegria.dogga.repository

import dev.lhalegria.dogga.model.BreedModel

interface IBreedRepository {

    suspend fun getBreeds(): Result<List<BreedModel>>

    suspend fun getSubBreed(masterBreed: String): Result<List<BreedModel>>

    suspend fun getBreedImage(breed: String): Result<String>
}
