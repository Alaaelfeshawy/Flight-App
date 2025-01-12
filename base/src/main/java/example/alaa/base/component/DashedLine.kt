package example.alaa.base.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DashedLine(modifier: Modifier = Modifier , color: Color = Color.LightGray) {
       val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
       Canvas(modifier
           .height(1.dp)
           .fillMaxWidth()
       ) {
           drawLine(
               color = color,
               start = Offset(0f, 0f),
               end = Offset(size.width, 0f),
               pathEffect = pathEffect
           )
       }
}