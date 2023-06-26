package dev.lhalegria.dogga.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.view.composable.BreedDetail
import dev.lhalegria.dogga.view.composable.BreedList
import dev.lhalegria.dogga.view.composable.EmptyDataBox
import dev.lhalegria.dogga.view.composable.ErrorBox
import dev.lhalegria.dogga.view.composable.LoadingBox
import dev.lhalegria.dogga.view.ui.theme.DoggaTheme
import dev.lhalegria.dogga.viewmodel.BreedViewModel
import dev.lhalegria.dogga.viewmodel.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BreedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getBreeds()

        setContent {
            DoggaTheme {
                val breedsState by viewModel.breedStateFlow.collectAsStateWithLifecycle()
                when (breedsState) {
                    RequestState.Loading -> LoadingBox()
                    is RequestState.Error -> ErrorBox()
                    is RequestState.Success -> SuccessContainer(breedsState)
                }
            }
        }
    }
}

@Composable
fun DogsApp(breeds: List<BreedModel> = listOf()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "breeds_list") {
        composable("breeds_list") {
            BreedList(breeds, navController)
        }
        composable(
            route = "breed_details/{breed}",
            arguments = listOf(navArgument("breed") {
                type = NavType.StringType
            })
        ) {
            BreedDetail(it.arguments?.getString("breed").orEmpty(), navController)
        }
    }
}

@Composable
fun SuccessContainer(state: RequestState<List<BreedModel>>?) {
    val breeds = (state as? RequestState.Success<List<BreedModel>>)?.data
    if (breeds?.isNotEmpty() == true) {
        DogsApp(breeds = breeds)
    } else {
        EmptyDataBox()
    }
}
