package example.alaa.registration.userinformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.registration.R
import example.alaa.registration.route.NavigationItem
import example.alaa.registration.userinformation.component.FormComponent
import example.alaa.registration.userinformation.component.PasswordCriteriaComponent

@Composable
fun UserInformationScreen(modifier: Modifier = Modifier,
                          navController: NavHostController?=null,
                          viewModel: UserInformationViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                UserInformationEvent.NavigateToSuccess ->  navController?.navigate(NavigationItem.Success.route){
                    popUpTo(NavigationItem.PhoneNumber.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    FlightApp(
        modifier = Modifier.fillMaxSize(),
        navigationButtonClicked = {
            navController?.popBackStack()
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ScreenHeader(
                modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                text = stringResource(R.string.almost_done),
            )
            ScreenSubtitle(
                modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                text = stringResource(R.string.please_enter_your_details_in_the_following_section),
            )

            FormComponent(modifier, state, viewModel)

            PasswordCriteriaComponent(state)
        }

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            PrimaryMainButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .height(56.dp)
                    .align(Alignment.CenterHorizontally),
                buttonText = stringResource(R.string.create_account),
                enabled = state.isButtonEnabled,
                color = if(state.isButtonEnabled) colorResource(example.alaa.base.R.color.white) else colorResource(example.alaa.base.R.color.grey),
                onButtonClick = {
                    viewModel.processIntent(UserInformationIntent.CreateAccount)
                }
            )
        }
    }
}
@Preview
@Composable
fun UserInformationPreview(){
    UserInformationScreen()
}