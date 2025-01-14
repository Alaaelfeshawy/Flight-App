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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import example.alaa.base.component.CustomField
import example.alaa.base.component.DashedLine
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.SecondButton
import example.alaa.base.component.Styles
import example.alaa.base.component.TripLine
import example.alaa.base.component.TripTime
import example.alaa.base.component.model.TripLocation
import example.alaa.payment.R
import example.alaa.payment.ui.route.NavigationItem

@Composable
fun PaymentScreen(modifier: Modifier = Modifier , navController: NavController?=null) {
    FlightApp{
        Box(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ){
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 5.dp
                    )
                ){
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
                        image =R.drawable.airplane_1 ,
                        imageInfo = "Asky Airlines",
                        imageInfoColor = Color.Gray
                    )

                    TripTime(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        departTimeLabel = "Depart",
                        departTimeValue = "07:00 AM",
                        arrivalTimeLabel = "Arrival",
                        arrivalTimeValue ="08:45 AM",
                        numOfStops = "0",
                        durationTime = "01h 45m"
                    )

                    DashedLine(modifier = Modifier.padding(horizontal = 12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp,
                                vertical = 12.dp
                            ),
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

                }

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp , vertical = 8.dp),
                    text = stringResource(R.string.payment_method) ,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                    )

                 LazyRow {

                 }

                Card(
                    modifier = Modifier.padding(horizontal = 12.dp , vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(
                        modifier =  Modifier.padding(8.dp)
                    ){
                        CustomField(
                            title = "Card number"
                        )

                        CustomField(
                            title = "Card Holder"
                        )

                        Row {
                            CustomField(
                                modifier = Modifier.weight(1f),
                                title = "CVV"
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                            CustomField(
                                modifier = Modifier.weight(1f),
                                title = "Expiry Date"
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SecondButton(
                    modifier = Modifier.height(56.dp),
                    buttonText = "Cancel"
                )
                PrimaryMainButton(
                    buttonText = "Confirm" ,
                    modifier = Modifier.height(56.dp),
                    ){
                    navController?.navigate(NavigationItem.BoardingPass.route)
                }
            }
        }
    }
}

@Preview
@Composable
fun PaymentScreenPreview(modifier: Modifier = Modifier) {
    PaymentScreen()
}