package dev.lhalegria.dogga.view.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import dev.lhalegria.dogga.view.ui.theme.Caramel

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
            containerColor = Caramel,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}
