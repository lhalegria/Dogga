package dev.lhalegria.dogga.view.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.view.BreedDetailsScreen
import dev.lhalegria.dogga.view.ui.theme.DoggaTheme
import dev.lhalegria.dogga.view.ui.theme.caramel

val dogs = listOf(
    BreedModel("vira-lata", ""),
    BreedModel("salsicha", ""),
    BreedModel("pit-bull", ""),
    BreedModel("pastor alemão", ""),
    BreedModel("lulu da pomerania", ""),
    BreedModel("pintcher", ""),
)

@Preview(showBackground = true)
@Composable
fun DogsListPreview() {
    DoggaTheme {
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

        // BreedListScreen(dogs = dogs, null)
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    DoggaTheme {
        BreedDetailsScreen("", null)
    }
}
