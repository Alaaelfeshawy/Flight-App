package example.alaa.base.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar (
    modifier: Modifier = Modifier,
    color : Color = Color.Black,
    onClick : ()-> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        ) {
        Icon(
            tint = color,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null
        )
    }
}