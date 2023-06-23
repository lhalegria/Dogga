package dev.lhalegria.dogga.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.view.ui.theme.DoggaTheme
import dev.lhalegria.dogga.view.ui.theme.caramel
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
                val breedsState by viewModel.breedStateFlow.collectAsState()
                when (breedsState) {
                    is RequestState.Loading -> {
                        LoadingScreen()
                    }
                    is RequestState.Success -> {
                        val breeds = (breedsState as? RequestState.Success<List<BreedModel>>)?.data
                        if (breeds?.isNotEmpty() == true) {
                            DogsApp(breeds = breeds)
                        } else {
                            // TODO: empty list state
                        }
                    }
                    is RequestState.Error -> {
                        // TODO: error state
                    }
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
            BreedListScreen(breeds, navController)
        }
        composable(
            route = "breed_details/{breed}",
            arguments = listOf(navArgument("breed") {
                type = NavType.StringType
            })
        ) {
            BreedDetailsScreen(it.arguments?.getString("breed").orEmpty(), navController)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(50.dp),
            progress = 1f,
            color = caramel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedListScreen(dogs: List<BreedModel>, navController: NavHostController?) {
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
                    BreedCard(dog = it) {
                        navController?.navigate("dog_details/${it.name}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                "Top app bar description",
                Modifier
                    .padding(12.dp)
                    .clickable { iconClickAction.invoke() }
            )
        },
        title = { Text(title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = caramel,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
fun BreedCard(dog: BreedModel, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            BreedPicture(dog.photo, 72.dp)
            BreedContent(dog.name, Alignment.Start)
        }
    }
}

@Composable
fun BreedPicture(pictureUrl: String, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pictureUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_dog_placeholder),
            contentDescription = "Breed picture description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(imageSize)
        )
    }
}

@Composable
fun BreedContent(breedName: String, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = breedName,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
