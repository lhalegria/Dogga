package dev.lhalegria.dogga.view.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.lhalegria.dogga.model.BreedModel

@Composable
fun AppNavigation(breeds: List<BreedModel> = listOf()) {
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
