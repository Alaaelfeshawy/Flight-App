package example.alaa.flightdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import example.alaa.base.component.DashedLine
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.TripTime

@Composable
fun FlightDetailsScreen(modifier: Modifier = Modifier, navController: NavHostController?=null) {

    FlightApp(
        modifier = modifier.verticalScroll(rememberScrollState())
    ){
        Card(
            modifier = modifier
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.White,
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    bottom = 24.dp
                )
            ){
                Row(
                    modifier = Modifier.padding(18.dp)
                ){
                    Text(
                        text = "Asky Airlines",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                        )
                }
                DashedLine()
                TripTime(
                    departTimeLabel = "Temale",
                    departTimeValue = "TEL",
                    arrivalTimeLabel = "Kumasi",
                    arrivalTimeValue = "KSM",
                    numOfStops = "0",
                    durationTime = "01h 45m"
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Temale International Airport",
                        color = Color.Black,
                        maxLines = 2,
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Kumasi International Airport",
                        color = Color.Black,
                        maxLines = 2,
                        textAlign = TextAlign.End,
                    )
                }

                TripTime(
                    departTimeLabel = "Date",
                    departTimeValue = "Tue, May 06",
                    arrivalTimeLabel = "Time",
                    arrivalTimeValue = "07:00 AM",
                )

                TripTime(
                    departTimeLabel = "Class",
                    departTimeValue = "Economy",
                    arrivalTimeLabel = "Hand Baggage",
                    arrivalTimeValue = "23Kg",
                )
                DashedLine()

                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Fare Summary",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )

                TripTime(
                    departTimeLabel = "Passenger(s)",
                    departTimeValue = "Adult (x1)",
                    arrivalTimeLabel = "Ticket Price",
                    arrivalTimeValue = "GHS 800",
                )

                DashedLine()
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 8.dp,
                            vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "GHS 800",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                DashedLine(color = colorResource(example.alaa.base.R.color.grey))
                Text(
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 12.dp
                    ),
                    text = "*Refundable (Penalty Applies).",
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
                PrimaryMainButton(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    buttonText = "Confirm",
                    onButtonClick = {}
                )

            }
        }
    }
}

@Preview
@Composable
fun FlightDetailsScreenPreview(){
    FlightDetailsScreen()
}