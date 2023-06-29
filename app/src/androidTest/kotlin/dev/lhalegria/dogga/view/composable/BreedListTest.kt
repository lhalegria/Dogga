package dev.lhalegria.dogga.view.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.model.BreedModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun `GIVEN BreedList screen WHEN load THEN show toolbar title`() {
        composeTestRule.setContent {
            BreedList(mockBreedList, rememberNavController())
        }

        val breedListTitle = context.resources.getString(R.string.breed_list)
        composeTestRule.onNodeWithText(breedListTitle).assertIsDisplayed()
    }

    @Test
    fun `GIVEN BreedList screen WHEN load THEN show all items`() {
        composeTestRule.setContent {
            BreedList(mockBreedList, rememberNavController())
        }

        mockBreedList.forEach {
            composeTestRule.onNodeWithText(it.name).assertExists()
        }
    }

    private companion object {
        val mockBreedList = listOf(
            BreedModel("Beagle"),
            BreedModel("Corgi"),
            BreedModel("Pit-Bull"),
            BreedModel("Golden Retrivier")
        )
    }
}
