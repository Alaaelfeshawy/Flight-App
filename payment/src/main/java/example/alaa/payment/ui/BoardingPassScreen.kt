package example.alaa.payment.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R
import example.alaa.base.component.DashedLine
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.TripTime

@Composable
fun BoardingPassScreen(){

    FlightApp(
        modifier = Modifier.fillMaxWidth(),
    ){
        Card(
            modifier = Modifier.fillMaxWidth()
                    .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
                ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            )
        ){
            Column(modifier = Modifier.fillMaxWidth()){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Passenger" ,
                            fontSize = 15.sp ,
                            fontWeight = FontWeight.Medium ,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(Icons.Default.Image , contentDescription = null)
                    }

                Row (
                    modifier = Modifier.fillMaxWidth().padding(
                            horizontal = 12.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                        Text(
                            text = "MacRaymond Idan" ,
                            color = colorResource(R.color.black_01),
                            fontWeight = FontWeight.Medium ,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text ="Asky Airlines" ,
                            fontWeight = FontWeight.Medium ,
                            fontSize = 12.sp
                        )
                    }

                DashedLine(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    color = Color.Black,
                    )

                TripTime(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp,horizontal =12.dp),
                    departTimeLabel = "Temale",
                    departTimeValue = "TEL",
                    arrivalTimeLabel = "Kumasi",
                    arrivalTimeValue ="KSM",
                    numOfStops = "0",
                    durationTime = "01h 45m"
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.weight(1f)
                                .padding(end = 6.dp),
                            text = "Temale International Airport",
                            color = Color.Black,
                            maxLines = 2,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier.weight(1f)
                                .padding(end = 6.dp),
                            text = "Kumasi International Airport",
                            color = Color.Black,
                            maxLines = 2,
                            textAlign = TextAlign.End,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    FlightDetailsRow(
                        label = "Flight Code",
                        value = "Asky - 005",
                        label1 = "Class" ,
                        value1 = "Economy" ,
                        label2 = "Seat",
                        value2 = "Seat F2",
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FlightDetailsRow(
                        label = "Date",
                        value = "Tue, May 06",
                        label1 = "Departure",
                        value1 = "07:00 AM",
                        label2 = "Arrival",
                        value2 = "08:45 AM"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    DashedLine(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = Color.Black,
                    )
                    FlightDetailsRow(
                        label = "Gate",
                        value = "Gate TT4",
                        label1 = "",
                        value1 = "",
                        label2 = "Terminal",
                        value2 = "T001"
                    )

                    DashedLine(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = Color.Black,
                    )

                }
        }

           Box(modifier = Modifier.fillMaxSize().padding(
               bottom = 24.dp
           )){
               PrimaryMainButton(
                   modifier = Modifier.fillMaxWidth()
                       .height(56.dp)
                       .align(Alignment.BottomEnd)
                       .padding(horizontal = 12.dp),
                   buttonText = "Download Ticket"
               )
           }
    }
}

@Composable
private fun FlightDetailsRow(
    label : String,
    value : String,
    label1 : String,
    value1 : String,
    label2 : String,
    value2 : String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label ,
            value = value,
            labelTextAlign = TextAlign.Start,
            valueTextAlign = TextAlign.Start
        )

        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label1 ,
            value = value1,
            labelTextAlign = TextAlign.Center,
            valueTextAlign = TextAlign.Center,
        )

        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label2 ,
            value = value2,
            labelTextAlign = TextAlign.End,
            valueTextAlign = TextAlign.End,
        )
    }
}

@Composable
private fun ColumnValues(
    modifier: Modifier = Modifier,
    label : String,
    value : String,
    labelTextAlign : TextAlign,
    valueTextAlign : TextAlign
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            textAlign = labelTextAlign,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = value,
            textAlign = valueTextAlign,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = colorResource(R.color.black_01),
        )
    }
}


@Preview
@Composable
fun BoardingPassScreenPreview(){
    BoardingPassScreen()
}