package dev.lhalegria.dogga.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lhalegria.dogga.DoggaApp
import dev.lhalegria.dogga.datasource.response.BreedImageResponse
import dev.lhalegria.dogga.datasource.response.BreedResponse
import dev.lhalegria.dogga.datasource.service.BreedService
import dev.lhalegria.dogga.exception.ResponseErrorException
import dev.lhalegria.dogga.exception.ResponseNoBodyException
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.model.mapper.BreedMapper
import dev.lhalegria.dogga.model.mapper.IBreedMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class BreedRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val service = mockk<BreedService>()

    private val mapper: IBreedMapper by lazy {
        BreedMapper()
    }

    private val repository: IBreedRepository by lazy {
        BreedRepository(service, mapper)
    }

    @Before
    fun setup() {
        mockkObject(DoggaApp)
        every { DoggaApp.appResources.getString(any()) } returns MOCK_STRING_RES
    }

    @Test
    fun `GIVEN service getBreeds returns success WHEN calls getBreeds THEN returns success`() = runTest {
        // GIVEN - Configuration
        val mockResponse = BreedResponse(
            breedsList = listOf("Spitz", "Pit-Bull"),
            status = "success"
        )

        val expectedResponse = Result.success(mapper.breedsResponseToModel(mockResponse))
        coEvery { service.getBreeds() } returns Response.success(mockResponse)

        // WHEN - Action/Execution
        val response = repository.getBreeds()

        // THEN - Assertion
        coVerify { service.getBreeds() }
        assertEquals(expectedResponse, response)
    }

    @Test
    fun `GIVEN service getBreeds returns success AND body is null WHEN calls getBreeds THEN returns no body exception`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseNoBodyException())
        coEvery { service.getBreeds() } returns Response.success(null)

        // WHEN - Action/Execution
        val response = repository.getBreeds()

        // THEN - Assertion
        coVerify { service.getBreeds() }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    @Test
    fun `GIVEN service getBreeds returns failure WHEN calls getBreeds THEN returns failure`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseErrorException(MOCK_ERROR_CODE))
        coEvery { service.getBreeds() } returns Response.error(MOCK_ERROR_CODE, "".toResponseBody())

        // WHEN - Action/Execution
        val response = repository.getBreeds()

        // THEN - Assertion
        coVerify { service.getBreeds() }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    @Test
    fun `GIVEN service getSubBreedsFromBreed returns success WHEN calls getSubBreed THEN returns success`() = runTest {
        // GIVEN - Configuration
        val mockResponse = BreedResponse(
            breedsList = listOf("Spitz", "Pit-Bull"),
            status = "success"
        )

        val expectedResponse = Result.success(mapper.breedsResponseToModel(mockResponse))
        coEvery { service.getSubBreedsFromBreed(any()) } returns Response.success(mockResponse)

        // WHEN - Action/Execution
        val response = repository.getSubBreed(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getSubBreedsFromBreed(any()) }
        assertEquals(expectedResponse, response)
    }

    @Test
    fun `GIVEN service getSubBreedsFromBreed returns success AND body is null WHEN calls getSubBreed THEN returns no body exception`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseNoBodyException())
        coEvery { service.getSubBreedsFromBreed(any()) } returns Response.success(null)

        // WHEN - Action/Execution
        val response = repository.getSubBreed(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getSubBreedsFromBreed(any()) }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    @Test
    fun `GIVEN service getSubBreedsFromBreed returns failure WHEN calls getSubBreed THEN returns failure`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseErrorException(MOCK_ERROR_CODE))
        coEvery { service.getSubBreedsFromBreed(any()) } returns Response.error(MOCK_ERROR_CODE, "".toResponseBody())

        // WHEN - Action/Execution
        val response = repository.getSubBreed(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getSubBreedsFromBreed(any()) }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    @Test
    fun `GIVEN service getBreedImage returns success WHEN calls getBreedImage THEN returns success`() = runTest {
        // GIVEN - Configuration
        val mockResponse = BreedImageResponse("http://anyUrl", "success")

        val expectedResponse = Result.success(mockResponse.imageUrl)
        coEvery { service.getBreedImage(any()) } returns Response.success(mockResponse)

        // WHEN - Action/Execution
        val response = repository.getBreedImage(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getBreedImage(any()) }
        assertEquals(expectedResponse, response)
    }

    @Test
    fun `GIVEN service getBreedImage returns success AND body is null WHEN calls getBreedImage THEN returns no body exception`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseNoBodyException())
        coEvery { service.getBreedImage(any()) } returns Response.success(null)

        // WHEN - Action/Execution
        val response = repository.getBreedImage(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getBreedImage(any()) }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    @Test
    fun `GIVEN service getBreedImage returns failure WHEN calls getBreedImage THEN returns failure`() = runTest {
        // GIVEN - Configuration
        val expectedResponse = Result.failure<List<BreedModel>>(ResponseErrorException(MOCK_ERROR_CODE))
        coEvery { service.getBreedImage(any()) } returns Response.error(MOCK_ERROR_CODE, "".toResponseBody())

        // WHEN - Action/Execution
        val response = repository.getBreedImage(MOCK_BREED)

        // THEN - Assertion
        coVerify { service.getBreedImage(any()) }
        assertEquals(expectedResponse.toString(), response.toString())
    }

    private companion object {
        const val MOCK_ERROR_CODE = 400
        const val MOCK_BREED = "cattledog"
        const val MOCK_STRING_RES = "any string"
    }
}
