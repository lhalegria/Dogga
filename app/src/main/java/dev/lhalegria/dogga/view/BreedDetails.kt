package dev.lhalegria.dogga.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(breed: String, navController: NavHostController?) {

    Scaffold(topBar = {
        Toolbar(
            title = "Breed details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // BreedPicture(breed.pictureUrl, 240.dp)
                // BreedContent(breed.name, Alignment.CenterHorizontally)
            }
        }
    }
}
