package dev.lhalegria.dogga.model.mapper

import dev.lhalegria.dogga.datasource.response.BreedResponse
import dev.lhalegria.dogga.model.BreedModel

class BreedMapper : IBreedMapper {

    override fun breedsResponseToModel(response: BreedResponse) =
        response.breedsList.map { BreedModel(it) }
}
