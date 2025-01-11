package example.alaa.registration.landingscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.registration.R
import example.alaa.registration.landingscreen.component.ButtonsComponent
import example.alaa.registration.landingscreen.component.FooterComponent

@Composable
fun LandingScreen(modifier: Modifier = Modifier , navController: NavController ?= null) {
    FlightApp(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        showNavigationButton = false,
    ) {

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Image(
                modifier = Modifier.aspectRatio(0.8f)
                    .fillMaxHeight(.7f),
                painter = painterResource(R.drawable.img),
                contentDescription = null
            )
            Column {
                ScreenHeader(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        ),
                    text = stringResource(R.string.welcome_to_fly_mac),
                    textAlignment = TextAlign.Center
                )
                ScreenSubtitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                    text = stringResource(R.string.the_safest_way_to_travel_in_toddy_s_world),
                    textAlign = TextAlign.Center
                )

                ButtonsComponent(modifier, navController)

            }
            FooterComponent(modifier)
        }
    }

}


@Composable
@Preview
fun LandingScreenPreview(){
    LandingScreen()
}