package example.alaa.payment.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R
import example.alaa.base.component.DashedLine
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.TripTime
import example.alaa.payment.ui.components.FlightDetailsRow

@Composable
fun BoardingPassScreen(){

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White
            ),
    ){
        Box(
            modifier = Modifier.weight(2.5f)
        ){
            BoardingPassCard()
            Spacer(Modifier.height(12.dp))
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(
                        horizontal = 18.dp,
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp,
                                vertical = 8.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Passenger",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Icon(Icons.Default.Image, contentDescription = null)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 12.dp,
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "MacRaymond Idan",
                            color = colorResource(R.color.black_01),
                            fontWeight = FontWeight.Medium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Asky Airlines",
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }

                    DashedLine(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = Color.Black,
                    )

                    TripTime(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 12.dp),
                        departTimeLabel = "Temale",
                        departTimeValue = "TEL",
                        arrivalTimeLabel = "Kumasi",
                        arrivalTimeValue = "KSM",
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
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 6.dp),
                            text = "Temale International Airport",
                            color = Color.Black,
                            maxLines = 2,
                            textAlign = TextAlign.Start,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
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
                        label1 = "Class",
                        value1 = "Economy",
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
                        modifier = Modifier
                            .fillMaxWidth()
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = Color.Black,
                    )

                }
            }
        }

           Box(modifier = Modifier
               .fillMaxSize()
               .weight(0.5f)
               .padding(
                   bottom = 24.dp
               )){
               PrimaryMainButton(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(56.dp)
                       .align(Alignment.BottomEnd)
                       .padding(horizontal = 12.dp),
                   buttonText = "Download Ticket"
               )
           }
    }
}

@Composable
fun BoardingPassCard() {
    val color =  colorResource(R.color.orange)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = color
            ),
    ) {
        Canvas(modifier = Modifier.wrapContentSize()) {
            translate(left = 500f, top = -300f) {
                drawCircle(color = color, radius = 400.dp.toPx())
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = {},
                ) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    text = stringResource(example.alaa.payment.R.string.boarding_pass) ,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Image(
                painter = painterResource(example.alaa.payment.R.drawable.successmark),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Text(
                modifier = Modifier.padding(12.dp),
                text = stringResource(example.alaa.payment.R.string.confirmed_label),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 14.sp
                )
            Text(
                text = stringResource(example.alaa.payment.R.string.booking_done_successfully),
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun BoardingPassScreenPreview(){
    BoardingPassScreen()
}