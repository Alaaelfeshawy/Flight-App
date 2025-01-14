package example.alaa.registration.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.base.component.otp.OtpAction
import example.alaa.base.component.otp.OtpSection
import example.alaa.registration.R
import example.alaa.registration.route.NavigationItem

@Composable
fun OtpScreen(modifier: Modifier = Modifier,
              navController: NavHostController?=null,
              viewModel: OtpViewModel =  hiltViewModel()
              ) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }
    val focusManager = LocalFocusManager.current
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                OTPEvent.NavigateToInfo -> navController?.navigate(NavigationItem.UserInformation.route){
                    popUpTo(NavigationItem.Otp.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    LaunchedEffect(state.focusedIndex) {
        state.focusedIndex?.let { index ->
            focusRequesters.getOrNull(index)?.requestFocus()
        }
    }

    LaunchedEffect(state.code, keyboardManager) {
        val allNumbersEntered = state.code.none { it == null }
        if(allNumbersEntered) {
            focusRequesters.forEach {
                it.freeFocus()
            }
            focusManager.clearFocus()
            keyboardManager?.hide()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                OTPEvent.NavigateToInfo ->  navController?.navigate(NavigationItem.UserInformation.route)
            }
        }
    }
    FlightApp(
        navigationButtonClicked = {navController?.popBackStack()}
    ) {
        ScreenHeader(
            modifier = modifier.padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
            text = stringResource(R.string.otp_verification),
            )
        ScreenSubtitle(
                modifier = modifier.padding(
                    horizontal = 20.dp,
                    vertical = 8.dp
                ),
                text = stringResource(R.string.otp_verification_desc),

            )

            OtpSection(
                state = state,
                focusRequesters = focusRequesters,
                onAction = { action ->
                    when(action) {
                        is OtpAction.OnEnterNumber -> {
                            if(action.number != null) {
                                focusRequesters[action.index].freeFocus()
                            }
                        }
                        else -> Unit
                    }
                    viewModel.onAction(action)
                },
            )

            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp
                    ),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(example.alaa.base.R.color.grey),
                        )
                    ) {
                        append(stringResource(R.string.didn_t_receive_code))
                    }

                    withStyle(
                        style = SpanStyle(
                            color = colorResource(example.alaa.base.R.color.orange),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(R.string.resend))
                    }
                },
                style = TextStyle(
                    fontSize = 14.sp
                ),
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp,
            )

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
                    buttonText = stringResource(R.string.verify),
                    enabled = state.isValid == true,
                    color = if(state.isValid == true) colorResource(example.alaa.base.R.color.white) else colorResource(example.alaa.base.R .color.grey),
                    onButtonClick = {
                       viewModel.processIntent(OTPIntent.VerifyOTP)
                    }
                )
            }
    }
}

@Preview
@Composable
fun OtpScreenPreview(){
    OtpScreen()
}