package dev.lhalegria.dogga.view.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.lhalegria.dogga.model.BreedModel

private const val BREED_LIST_ROUTE = "breeds_list"
private const val BREED_NAME_ARG = "_arg_breed_name"
const val BREED_DETAIL_ROUTE = "breed_details"

@Composable
fun AppNavigation(breeds: List<BreedModel> = listOf()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BREED_LIST_ROUTE) {
        composable(BREED_LIST_ROUTE) {
            BreedList(breeds, navController)
        }
        composable(
            route = "$BREED_DETAIL_ROUTE/{$BREED_NAME_ARG}",
            arguments = listOf(navArgument(BREED_NAME_ARG) {
                type = NavType.StringType
            })
        ) {
            BreedDetail(it.arguments?.getString(BREED_NAME_ARG).orEmpty(), navController)
        }
    }
}
