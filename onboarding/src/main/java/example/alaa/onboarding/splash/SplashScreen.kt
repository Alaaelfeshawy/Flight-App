package example.alaa.onboarding.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import example.alaa.onboarding.R
import example.alaa.onboarding.route.NavigationItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavHostController?=null){

    LaunchedEffect(Unit) {
        delay(300)

        navController?.navigate(NavigationItem.Onboarding.route) {
            popUpTo(NavigationItem.Splash.route) {
                inclusive = true
            }
        }
    }

    Column(
            modifier = modifier.fillMaxSize()
                .background(colorResource(R.color.orange)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.applogo),
                contentDescription = null
            )

    }


}

@Preview
@Composable
fun Preview(){
    SplashScreen()
}