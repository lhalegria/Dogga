package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.viewmodel.BreedViewModel
import dev.lhalegria.dogga.viewmodel.RequestState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetail(
    breed: String,
    navController: NavHostController?,
    viewModel: BreedViewModel = koinViewModel()
) {
    viewModel.getBreedImage(breed)

    val imageState by viewModel.breedImageStateFlow.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        Toolbar(
            title = "Breed details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                when (imageState) {
                    RequestState.Loading -> BreedImage(name = breed)
                    is RequestState.Error -> BreedImage(name = breed)
                    is RequestState.Success ->
                        BreedImage(name = breed, url = (imageState as? RequestState.Success<String>)?.data.orEmpty())
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 10.dp),
                    text = breed.replaceFirstChar { it.uppercaseChar() },
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Composable
fun BreedImage(name: String, url: String = "") {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_dog_placeholder),
        error = painterResource(id = R.drawable.ic_dog_placeholder),
        contentDescription = "Picture of a dog from the $name breed.",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
            .padding(0.dp)
    )
}
