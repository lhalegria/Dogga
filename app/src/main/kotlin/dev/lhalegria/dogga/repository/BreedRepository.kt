package dev.lhalegria.dogga.repository

import dev.lhalegria.dogga.datasource.service.BreedsService
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.model.mapper.IBreedMapper
import retrofit2.Response
import java.io.IOException

class BreedRepository(
    private val service: BreedsService,
    private val mapper: IBreedMapper
) : IBreedRepository {

    override suspend fun getBreeds(): Result<List<BreedModel>> =
        getResultFromResponse(service.getBreeds())
            .map { mapper.breedsResponseToModel(it) }

    override suspend fun getSubBreed(masterBreed: String): Result<List<BreedModel>> =
        getResultFromResponse(service.getSubBreedsFromBreed(masterBreed))
            .map { mapper.breedsResponseToModel(it) }

    override suspend fun getBreedImage(breed: String): Result<String> =
        getResultFromResponse(service.getBreedImage(breed))
            .map { it.imageUrl }

    private fun <T> getResultFromResponse(response: Response<T>): Result<T> =
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: throw IOException("Response has no body")
            } else {
                Result.failure(Exception("Response failure with code:${response.code()}"))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
}
