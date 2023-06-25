package dev.lhalegria.dogga.model.mapper

import dev.lhalegria.dogga.datasource.response.BreedsResponse
import dev.lhalegria.dogga.model.BreedModel

interface IBreedMapper {

    fun breedsResponseToModel(response: BreedsResponse): List<BreedModel>
}
