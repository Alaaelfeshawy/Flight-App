package example.alaa.registration.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.registration.landingscreen.LandingScreen
import example.alaa.registration.login.LoginScreen
import example.alaa.registration.otp.OtpScreen
import example.alaa.registration.phonenumber.PhoneNumberScreen
import example.alaa.registration.success.SuccessScreen
import example.alaa.registration.userinformation.UserInformationScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Landing.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.Landing.route) {
            LandingScreen(modifier , navController)
        }
        composable(NavigationItem.Otp.route) {
            OtpScreen(modifier , navController)
        }
        composable(NavigationItem.PhoneNumber.route) {
            PhoneNumberScreen(modifier , navController)
        }
        composable(NavigationItem.Success.route) {
            SuccessScreen(modifier , navController)
        }
        composable(NavigationItem.UserInformation.route) {
            UserInformationScreen(modifier , navController)
        }
        composable(NavigationItem.Login.route) {
            LoginScreen(modifier , navController)
        }
    }
}