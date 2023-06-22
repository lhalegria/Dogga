package dev.lhalegria.dogga.model.mapper

import dev.lhalegria.dogga.datasource.response.BreedsResponse
import dev.lhalegria.dogga.model.BreedModel

class BreedsMapper : IBreedMapper {

    override fun breedsResponseToModel(response: BreedsResponse) =
        response.breedsList.map { BreedModel(it) }
}
