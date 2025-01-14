package example.alaa.flightdetails.ui.seatselection

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenSubtitle
import example.alaa.base.component.TabBarBackground
import example.alaa.flightdetails.ui.seatselection.components.FlightSeatLayout
import example.alaa.flightdetails.ui.seatselection.components.SeatsStatus
import example.alaa.payment.ui.PaymentActivity
import example.alaa.searchresult.R

@Composable
fun SeatSelectionScreen(modifier: Modifier = Modifier,
                        navController: NavHostController?=null
) {

    val viewmodel = hiltViewModel<SeatSelectionViewModel>()
    val state = viewmodel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.events.collect{
            when(it){
                is SeatSelectionEvents.NavigateToPayment -> {
                    navigateToPaymentScreen(context)
                }
            }
        }
    }
    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage != null){
            Toast.makeText(context , state.errorMessage , Toast.LENGTH_LONG).show()
        }
    }
    FlightApp(
        modifier = modifier.verticalScroll(rememberScrollState()),
        title = stringResource(R.string.seat_selection),
        color = colorResource(example.alaa.base.R.color.orange),
        backArrowColor = Color.White,
        navigationButtonClicked = {
            navController?.popBackStack()
        }
        ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(colorResource(example.alaa.base.R.color.orange))
        ){
            TabBarBackground()
        }
        SeatsStatus()

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)){

            ScreenSubtitle(
                text = "Class",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 8.dp
                    ),
            )
            ScreenSubtitle(
                text = "Economy",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            FlightSeatLayout(
                seats = state.seats ,
                columns = state.columns ,
                rows = 9
            ){
                viewmodel.processIntent(SeatSelectionIntent.SetSeat(it))
            }

        }

        PrimaryMainButton(
            buttonText = "Confirm Seats",
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(56.dp),
            onButtonClick = {
                viewmodel.processIntent(SeatSelectionIntent.ConfirmSeats)
            }
        )
    }
}

private fun navigateToPaymentScreen(context: Context,){
    context.startActivity(Intent( context, PaymentActivity::class.java))
}

@Preview
@Composable
fun SeatSelectionPreview(){
    SeatSelectionScreen()
}