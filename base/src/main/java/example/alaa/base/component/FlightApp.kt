package example.alaa.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FlightApp(
    modifier : Modifier = Modifier,
    showNavigationButton : Boolean = true,
    title : String?=null,
    color : Color = Color.White,
    titleColor : Color = Color.White,
    backArrowColor : Color = Color.Black,
    navigationButtonClicked : () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Color.White,
        modifier = Modifier
            .padding(
                top = 12.dp
            ),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().background(color = color),
                verticalAlignment = Alignment.CenterVertically
            ){
                if (showNavigationButton)
                    AppBar(
                        onClick = navigationButtonClicked,
                        color = backArrowColor
                    )
                if (title != null)
                    Text(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                horizontal = 12.dp
                            ),
                        text = title ,
                        color = titleColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold
                    )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .imePadding()
        ) {
            content()
        }
    }
}