package example.alaa.base.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar (
    onClick : ()-> Unit
) {
    IconButton( onClick = onClick) {
        Icon(
            tint = Color.Black,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null
        )
    }
}