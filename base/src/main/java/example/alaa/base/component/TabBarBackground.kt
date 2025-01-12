package example.alaa.base.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun TabBarBackground() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        val width = size.width
        val height = size.height
        val path = Path().apply {
            moveTo(0f, height * 0.8f) // Starting point on the left side
            cubicTo(
                width * 0.25f, height * 0.6f,  // Control point 1 (left curve)
                width * 0.75f, height * 1.0f,  // Control point 2 (right curve)
                width, height * 0.8f           // End point on the right side
            )
            lineTo(width, height)  // Bottom-right corner
            lineTo(0f, height)     // Bottom-left corner
            close()
        }
        drawPath(path, color = Color.White)
    }
}