package example.alaa.base.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R

@Composable
fun SecondButton(modifier: Modifier = Modifier,
               buttonText : String,
               enabled : Boolean = true,
               buttonColors : ButtonColors = ButtonDefaults.buttonColors(
                   contentColor = colorResource(example.alaa.base.R.color.white) ,
                   containerColor = colorResource(example.alaa.base.R.color.white),
                   disabledContentColor = colorResource(example.alaa.base.R.color.white),
                   disabledContainerColor = colorResource(example.alaa.base.R.color.white),
               ),
               onButtonClick: () -> Unit ={},
               ) {
    Button(
        modifier = modifier,
        onClick = onButtonClick,
        colors = buttonColors ,
        enabled = enabled,
        border = BorderStroke(
            1.dp,
            color = colorResource(R.color.orange)
        )
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                color = colorResource(R.color.orange),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview
@Composable
fun SecondButtonPreview(){
    SecondButton(
        buttonText = "Sign up" ,
        )
}