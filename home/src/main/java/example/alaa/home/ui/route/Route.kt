package example.alaa.home.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.home.ui.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Home.route) {
            HomeScreen(modifier , navController)
        }
    }
}