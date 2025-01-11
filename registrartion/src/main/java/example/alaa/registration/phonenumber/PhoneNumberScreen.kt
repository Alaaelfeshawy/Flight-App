package example.alaa.registration.phonenumber

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.registration.R
import example.alaa.registration.phonenumber.components.AlreadyHaveAccountComponent
import example.alaa.registration.phonenumber.components.PhoneNumberComponent
import example.alaa.registration.route.NavigationItem

@Composable
fun PhoneNumberScreen(
    modifier: Modifier = Modifier ,
    navController: NavController? = null,
    viewModel: PhoneNumberViewModel = hiltViewModel()
    ) {
    val state = viewModel.state.collectAsState().value
    val buttonEnabled = state.isValidate && state.phoneNumber.isNotEmpty()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                PhoneNumberEvent.NavigateToOtp -> navController?.navigate(NavigationItem.Otp.route)
            }
        }
    }

    FlightApp(
        navigationButtonClicked = {
            navController?.popBackStack()
        }
    ){
        ScreenHeader(
                    modifier = modifier.padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                    text = stringResource(R.string.can_you_input_your_number),
                )
        ScreenSubtitle(
                    modifier = modifier.padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                    text = stringResource(R.string.verifiy_desc),
                )

        PhoneNumberComponent(modifier, state, viewModel)

                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    PrimaryMainButton(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .height(54.dp)
                            .align(Alignment.CenterHorizontally),
                        buttonText = stringResource(R.string.send_code),
                        enabled = buttonEnabled,
                        color = if(buttonEnabled) colorResource(example.alaa.base.R.color.white) else colorResource(example.alaa.base.R.color.grey),
                        onButtonClick = {
                            viewModel.processIntent(PhoneNumberIntent.VerifyPhoneNumber)
                        }
                    )
                    AlreadyHaveAccountComponent(
                        modifier = modifier,
                        navController = navController
                    )
                }
            }
}


@Preview
@Composable
fun RegistrationScreenPreview(){
    PhoneNumberScreen()
}