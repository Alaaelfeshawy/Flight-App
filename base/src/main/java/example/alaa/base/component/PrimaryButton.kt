package example.alaa.base.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import example.alaa.base.R

@Composable
fun PrimaryMainButton(modifier: Modifier = Modifier,
               buttonText : String,
               enabled : Boolean = true,
               buttonColors : ButtonColors = ButtonDefaults.buttonColors(
                   containerColor = colorResource(R.color.orange),
                   contentColor = colorResource(R.color.white),
                   disabledContentColor = colorResource(R.color.light_grey),
                   disabledContainerColor = colorResource(R.color.light_grey),
               ),
               color: Color = Color.White,
               onButtonClick: () -> Unit ={},
               ) {
    Button(
        modifier = modifier,
        onClick = onButtonClick,
        colors = buttonColors,
        enabled = enabled
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                color = color,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Preview
@Composable
fun MainButtonPreview(){
    PrimaryMainButton(buttonText = "Sign up")
}