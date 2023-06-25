package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.lhalegria.dogga.model.BreedModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedList(dogs: List<BreedModel>, navController: NavHostController?) {
    Scaffold(topBar = {
        Toolbar(
            title = "Breeds list",
            icon = Icons.Default.Home
        ) {}
    }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                items(dogs) {
                    BreedListItem(dog = it) {
                        navController?.navigate("dog_details/${it.name}")
                    }
                }
            }
        }
    }
}
