package dev.lhalegria.dogga.model.mapper

import dev.lhalegria.dogga.datasource.response.BreedResponse
import dev.lhalegria.dogga.model.BreedModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class BreedMapperTest {

    private lateinit var mapper: IBreedMapper

    @Before
    fun setup() {
        mapper = BreedMapper()
    }

    @Test
    fun `GIVEN BreedResponse object WHEN breedsResponseToModel is called THEN return same properties`() {
        val response = BreedResponse(
            breedsList = listOf("Spitz", "Pit-Bull"),
            status = "success"
        )

        val expected = listOf(
            BreedModel("Spitz"),
            BreedModel("Pit-Bull")
        )

        val result = mapper.breedsResponseToModel(response)

        assertEquals(expected, result)
    }

    @Test
    fun `GIVEN BreedResponse object WHEN breedsResponseToModel is called THEN return different properties`() {
        val response = BreedResponse(
            breedsList = listOf("Corgi", "Beagle"),
            status = "success"
        )

        val expected = listOf(
            BreedModel("Spitz"),
            BreedModel("Pit-Bull")
        )

        val result = mapper.breedsResponseToModel(response)

        assertNotEquals(expected, result)
    }
}
