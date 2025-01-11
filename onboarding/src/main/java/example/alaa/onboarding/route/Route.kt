package example.alaa.onboarding.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.onboarding.onboarding.Onboarding
import example.alaa.onboarding.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Splash.route) {
            SplashScreen(modifier , navController)
        }
        composable(NavigationItem.Onboarding.route) {
            Onboarding()
        }
    }
}

