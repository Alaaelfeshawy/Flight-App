package example.alaa.searchresult.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.component.Styles
import example.alaa.base.component.TripLine
import example.alaa.base.component.TripTime
import example.alaa.base.component.model.TripLocation
import example.alaa.flightdetails.ui.FlightDetailsActivity
import example.alaa.searchresult.route.BookingSection


@Composable
fun FlightItem(modifier: Modifier = Modifier, context : Context) {

    Card(
        modifier = Modifier.padding(horizontal = 12.dp ,
            vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ){
        Column {
            TripLine(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                startLocation = TripLocation(
                    title = "TML",
                    subtitle = "Temale",
                    titleStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    subtitleStyle= TextStyle(
                        color = Color.Gray,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                    ),
                ),
                endLocation = TripLocation(
                    title = "KMS",
                    subtitle = "Kumasi",
                    titleStyle = Styles.TripLineStyles.titleStyle
                        .copy(
                            color = Color.Black,
                            textAlign = TextAlign.End
                        ),
                    subtitleStyle = Styles.TripLineStyles.subtitleStyle.copy(
                        color = Color.Gray,
                        textAlign = TextAlign.End
                    ),
                ),
                image = example.alaa.searchresult.R.drawable.airplane_1,
                imageInfo = "Asky Airlines",
                imageInfoColor = Color.Gray
            )

            TripTime(
                modifier =modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                departTimeLabel = "Depart",
                departTimeValue = "07:00 AM",
                arrivalTimeLabel = "Arrival",
                arrivalTimeValue ="08:45 AM",
                numOfStops = "0",
                durationTime = "01h 45m"
            )


            BookingSection( modifier = Modifier.padding(top = 12.dp)){
                navigateToFlightDetailsScreen(context)
            }

        }
    }
}

private fun navigateToFlightDetailsScreen(context: Context){
    context.startActivity(Intent( context, FlightDetailsActivity::class.java).apply {
//        val mBundle = Bundle()
//        mBundle.putParcelable(SEARCH_MODEL,searchModel)
//        putExtras(mBundle)
    })
}