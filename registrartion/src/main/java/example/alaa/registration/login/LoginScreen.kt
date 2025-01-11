package example.alaa.registration.login

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import example.alaa.base.component.CustomField
import example.alaa.base.component.FlightApp
import example.alaa.base.component.PasswordInputField
import example.alaa.base.component.PrimaryMainButton
import example.alaa.home.ui.HomeActivity
import example.alaa.registration.R

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController?=null) {
    val viewModel : LoginViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect{
            when(it){
                LoginEvent.NavigateToHome -> {
                    navigateToHomeScreen(context)
                }
            }
        }
    }
    FlightApp (
        modifier = modifier
            .padding(
                top = 12.dp
            ),
        navigationButtonClicked = {
            navController?.popBackStack()
        }
    ) {
            Text(
                modifier = modifier.padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                ),
                text = stringResource(R.string.log_in),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp
                )
            )

            CustomField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                value = state.userName.first ,
                onValueChange = {
                    viewModel.processIntent(LoginIntent.ValidateUsername(it))
                },
                isError = !state.userName.second && state.userName.first.isNotBlank(),
                errorMessage = stringResource(R.string.invalid_username),
                placeholder = {
                    Text(
                        stringResource(R.string.enter_email_or_phone_number),
                        color = Color.LightGray
                    )
                }
            )
            PasswordInputField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ),
                password = state.password.first,
                onPasswordChange = {
                    viewModel.processIntent(LoginIntent.ValidatePassword(it))
                },
                isError = !state.password.second && state.password.first.isNotBlank(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_password_placeholder),
                        color = Color.LightGray

                    )
                }
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
                        .height(54.dp)
                        .align(Alignment.CenterHorizontally),
                    buttonText = stringResource(R.string.login),
                    enabled = state.isButtonEnabled,
                    color = if(state.isButtonEnabled) colorResource(example.alaa.base.R.color.white) else colorResource(example.alaa.base.R.color.grey),
                    onButtonClick = {
                        viewModel.processIntent(LoginIntent.Login)
                    }
                )
            }
        }
}

private fun navigateToHomeScreen(context: Context){
    context.startActivity(Intent( context, HomeActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })
}

@Preview
@Composable
fun LoginPreview(){
    LoginScreen()
}