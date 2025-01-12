package example.alaa.payment.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Payment.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Payment.route) {

        }

        composable(NavigationItem.BoardingPass.route) {

        }
    }
}