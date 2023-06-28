package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.view.ui.theme.CaramelStrong

@Composable
fun EmptyDataBox(action: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(32.dp),
            fontSize = TextUnit(15f, TextUnitType.Sp),
            textAlign = TextAlign.Center,
            color = CaramelStrong,
            text = stringResource(id = R.string.empty_data_message)
        )

        TryAgainButton(modifier = Modifier) {
            action.invoke()
        }
    }
}
