package example.alaa.base.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import example.alaa.base.component.model.TripLocation

@Composable
fun TripLine(
    modifier: Modifier = Modifier,
    startLocation: TripLocation,
    endLocation: TripLocation,
    image: Int,
    imageInfo: String,
    imageInfoColor: Color = Color.White
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TripDetails(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            title = startLocation.title,
            subTitle = startLocation.subtitle,
            subTitleStyle = startLocation.subtitleStyle,
            titleStyle = startLocation.titleStyle
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(54.dp),
                painter = painterResource(image),
                contentDescription = null
            )
            Text(text = imageInfo, style = Styles.TripLineStyles.subtitleStyle, color = imageInfoColor)
        }

        TripDetails(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            title = endLocation.title,
            subTitle = endLocation.subtitle,
            subTitleStyle = endLocation.subtitleStyle,
            titleStyle = endLocation.titleStyle
        )
    }
}