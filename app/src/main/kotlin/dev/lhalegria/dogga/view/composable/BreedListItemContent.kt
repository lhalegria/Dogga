package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.lhalegria.dogga.R

@Composable
fun BreedListItemText(breedName: String, alignment: Alignment.Horizontal) {
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

@Composable
fun BreedListItemPicture(pictureUrl: String, imageSize: Dp) {
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
