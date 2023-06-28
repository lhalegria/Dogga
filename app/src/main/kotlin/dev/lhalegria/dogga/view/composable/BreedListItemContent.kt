package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lhalegria.dogga.R

@Composable
fun BreedListItemText(breedName: String, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = alignment
    ) {
        Text(
            text = breedName.replaceFirstChar { it.uppercaseChar() },
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun BreedListItemIcon() {
    Image(
        painterResource(R.drawable.ic_dog_face),
        contentDescription = stringResource(id = R.string.dog_icon_content_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .padding(2.dp)
    )
}
