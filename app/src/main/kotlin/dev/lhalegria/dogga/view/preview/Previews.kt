package dev.lhalegria.dogga.view.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.lhalegria.dogga.model.BreedModel
import dev.lhalegria.dogga.view.composable.BreedDetail
import dev.lhalegria.dogga.view.composable.BreedList
import dev.lhalegria.dogga.view.composable.EmptyDataBox
import dev.lhalegria.dogga.view.composable.ErrorBox
import dev.lhalegria.dogga.view.composable.LoadingBox
import dev.lhalegria.dogga.view.ui.theme.DoggaTheme

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
fun BreedLoadingPreview() {
    DoggaTheme {
        LoadingBox()
    }
}

@Preview(showBackground = true)
@Composable
fun BreedEmptyDataPreview() {
    DoggaTheme {
        EmptyDataBox()
    }
}

@Preview(showBackground = true)
@Composable
fun BreedErrorDataPreview() {
    DoggaTheme {
        ErrorBox()
    }
}

@Preview(showBackground = true)
@Composable
fun BreedListPreview() {
    DoggaTheme {
        BreedList(dogs = dogs, null)
    }
}

@Preview(showBackground = true)
@Composable
fun BreedDetailsPreview() {
    DoggaTheme {
        BreedDetail("", null)
    }
}
