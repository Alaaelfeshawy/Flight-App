package example.alaa.onboarding.onboarding

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import example.alaa.base.component.FlightApp
import example.alaa.onboarding.R
import example.alaa.onboarding.component.PagerContent
import example.alaa.registration.RegistrationActivity
import kotlinx.coroutines.launch

@Composable
fun Onboarding(modifier: Modifier = Modifier ,
               navController: NavHostController = rememberNavController()) {

    FlightApp(modifier = modifier , showNavigationButton = false) {
            val context = LocalContext.current

            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = {
                3
            })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when(page){
                    0-> {
                        PagerContent(modifier = Modifier ,
                            pagerState = pagerState,
                            image = R.drawable.plane1,
                            title = stringResource(R.string.easy_to_use_easy_flight_search),
                            subTitle = stringResource(R.string.user_friendly_interface_efficient_flight_search),
                            onSkipClicked = {
                                navigateToRegistrationScreen(context)
                            },
                            onNextClicked = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(1)

                                }
                            }
                        )
                    }
                    1-> {
                        PagerContent(modifier = Modifier ,
                            pagerState = pagerState,
                            image = R.drawable.plane2,
                            title = stringResource(R.string.quick_booking_real_time_flight_alerts),
                            subTitle = stringResource(R.string.enjoy_a_hassle_free_booking_and_instant_flight_status_updates),
                            onSkipClicked = {
                                navigateToRegistrationScreen(context)
                            },
                            onNextClicked = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(2)
                                }
                            }
                        )
                    }
                    2->{
                        PagerContent(modifier = Modifier ,
                            pagerState = pagerState,
                            image = R.drawable.plane3,
                            title = stringResource(R.string.fast_and_secure_payment_options),
                            subTitle = stringResource(R.string.secure_trusted_payments_protecting_your_financial_information),
                            onNextClicked = {
                                navigateToRegistrationScreen(context)
                            },
                            isLastPage = true
                        )
                    }
                }

            }
        }

}


private fun navigateToRegistrationScreen(context: Context){
    context.startActivity(Intent( context, RegistrationActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })
}

@Preview
@Composable
fun Onboarding1Preview(){
    Onboarding()
}