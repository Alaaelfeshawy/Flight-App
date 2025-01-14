package example.alaa.base.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import example.alaa.base.R

@Composable
fun TripTime(
    modifier: Modifier = Modifier,
    departTimeLabel: String,
    departTimeValue: String,
    arrivalTimeLabel: String,
    arrivalTimeValue: String,
    numOfStops: String?=null,
    durationTime: String?=null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TripDetails(
            modifier = Modifier
                .weight(1f),
            title = departTimeLabel,
            subTitle = departTimeValue,
            titleStyle = Styles.TripTimeStyles.departTimeLabelStyle,
            subTitleStyle = Styles.TripTimeStyles.departTimeValueStyle
        )

        if (numOfStops != null && durationTime != null){
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = durationTime, style = Styles.TripTimeStyles.subtitleTextStyle)

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .size(6.dp)
                    )
                    Box(modifier = Modifier.weight(1f)) {
                        DashedLine(modifier = Modifier.align(Alignment.Center))
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            tint = colorResource(R.color.orange),
                            painter = painterResource(R.drawable.airplane),
                            contentDescription = null
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .size(6.dp)
                    )
                }

                Text(
                    text = "$numOfStops ${stringResource(id = R.string.stop)}",
                    style = Styles.TripTimeStyles.subtitleTextStyle
                )
            }
        }

        TripDetails(
            modifier = Modifier
                .weight(1f),
            title = arrivalTimeLabel,
            subTitle = arrivalTimeValue,
            titleStyle = Styles.TripTimeStyles.departTimeLabelStyle.copy(
                textAlign = TextAlign.End
            ),
            subTitleStyle = Styles.TripTimeStyles.departTimeValueStyle.copy(
                textAlign = TextAlign.End
            )
        )
    }
}