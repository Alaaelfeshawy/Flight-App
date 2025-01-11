package example.alaa.base.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeader(
    modifier : Modifier = Modifier,
    text : String,
    color : Color = Color.Black,
    textAlignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            textAlign = textAlignment,
            color = color
        )
    )
}