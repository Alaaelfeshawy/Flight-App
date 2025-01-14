package example.alaa.base.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TripDetails(modifier: Modifier = Modifier,
                title : String,
                subTitle : String,
                titleStyle: TextStyle,
                subTitleStyle: TextStyle
) {
    Column(
        modifier = modifier,
    ){
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = titleStyle,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subTitle,
            style = subTitleStyle,
            maxLines = 2,
        )
    }
}