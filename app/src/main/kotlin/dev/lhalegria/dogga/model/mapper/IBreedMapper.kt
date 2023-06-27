package dev.lhalegria.dogga.model.mapper

import dev.lhalegria.dogga.datasource.response.BreedResponse
import dev.lhalegria.dogga.model.BreedModel

interface IBreedMapper {

    fun breedsResponseToModel(response: BreedResponse): List<BreedModel>
}
