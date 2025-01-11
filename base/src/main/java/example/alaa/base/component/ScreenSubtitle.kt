package example.alaa.base.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ScreenSubtitle(
    modifier : Modifier = Modifier,
    text : String,
    color : Color = colorResource(example.alaa.base.R.color.grey),
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 14.sp
        ),
        color = color,
        textAlign = textAlign
    )
}