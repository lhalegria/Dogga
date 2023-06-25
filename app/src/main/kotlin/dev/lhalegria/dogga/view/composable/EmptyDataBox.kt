package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import dev.lhalegria.dogga.R
import dev.lhalegria.dogga.view.ui.theme.caramelStrong

@Composable
fun EmptyDataBox() {
    Box(Modifier.fillMaxSize()) {
        Box(Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = caramelStrong,
                text = stringResource(id = R.string.empty_data_message)
            )

            TryAgainButton(modifier = Modifier.align(Alignment.BottomCenter)) {

            }
        }
    }
}
