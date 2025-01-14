package example.alaa.payment.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.payment.ui.BoardingPassScreen
import example.alaa.payment.ui.PaymentScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = NavigationItem.Payment.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Payment.route) {
            PaymentScreen(navController = navController)
        }

        composable(NavigationItem.BoardingPass.route) {
            BoardingPassScreen()
        }
    }
}