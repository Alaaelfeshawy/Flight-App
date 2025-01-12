package example.alaa.searchresult.route

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import example.alaa.base.component.DashedLine
import example.alaa.base.component.Styles
import example.alaa.base.component.TripDetails


@Composable
fun BookingSection(
    modifier: Modifier = Modifier ,
    onClicked : () -> Unit
) {
    Box(
        modifier = modifier
            .background(colorResource(id = example.alaa.base.R.color.orange_01)) // Use colorResource for consistent color usage
    ) {
        Surface(
            modifier = Modifier
                .height(15.dp)
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
                .offset(y = (-8).dp),
            shape = RoundedCornerShape(
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            ),
            color = colorResource(id = example.alaa.base.R.color.orange_01)
        ) {
            DashedLine(color = Color.Black) // Implement DashedLine composable as needed
        }

        Row(
            modifier = Modifier.padding(
                horizontal = 12.dp,
            ),
        ) {
            TripDetails(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                title = "Ticket Price",
                subTitle = "GHS 800",
                titleStyle = Styles.TripLineStyles.titleStyle,
                subTitleStyle = Styles.TripLineStyles.subtitleStyle.copy(
                    fontWeight = FontWeight.Medium
                )
            )

            Button(
                onClick = { onClicked.invoke() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = example.alaa.base.R.color.orange_02)
                )
            ) {
                Text("Book Now")
            }
        }
    }
}




@Preview
@Composable
fun BookingSectionPreview(){
    BookingSection(){

    }
}