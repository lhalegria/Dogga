package dev.lhalegria.dogga.view.composable

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.view.ui.theme.Caramel

@Composable
fun TryAgainButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Caramel,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text(text = stringResource(id = R.string.try_again))
    }
}
