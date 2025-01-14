package example.alaa.flightdetails.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.flightdetails.ui.flightdetails.FlightDetailsScreen
import example.alaa.flightdetails.ui.seatselection.SeatSelectionScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.FlightDetails.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.FlightDetails.route) {
            FlightDetailsScreen(modifier , navController)
        }
        composable(NavigationItem.SeatSelection.route) {
            SeatSelectionScreen(modifier , navController)
        }
    }
}