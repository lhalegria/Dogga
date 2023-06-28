package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.model.BreedModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedList(
    dogs: List<BreedModel>,
    navController: NavHostController?
) {
    Scaffold(topBar = {
        Toolbar(
            title = stringResource(id = R.string.breed_list),
            icon = ImageVector.vectorResource(id = R.drawable.ic_toolbar_icon)
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
                        navController?.navigate("$BREED_DETAIL_ROUTE/${it.name}")
                    }
                }
            }
        }
    }
}
