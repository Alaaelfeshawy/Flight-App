package example.alaa.base.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FlightApp(
    modifier : Modifier = Modifier,
    showNavigationButton : Boolean = true,
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
            Row {
                if (showNavigationButton)
                    AppBar(
                        onClick = navigationButtonClicked
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