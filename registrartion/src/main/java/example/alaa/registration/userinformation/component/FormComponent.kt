package example.alaa.registration.userinformation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import example.alaa.base.component.CustomField
import example.alaa.base.component.PasswordInputField
import example.alaa.registration.R
import example.alaa.registration.userinformation.UserInformationIntent
import example.alaa.registration.userinformation.UserInformationState
import example.alaa.registration.userinformation.UserInformationViewModel

@Composable
fun FormComponent(
    modifier: Modifier,
    state: UserInformationState,
    viewModel: UserInformationViewModel
) {
    CustomField(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        value = state.userName,
        onValueChange = { userName ->
            viewModel.processIntent(UserInformationIntent.SetUsername(userName))
        },
        isError = !state.isUserNameValid && state.userName.isNotBlank(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        errorMessage = "User name should be greater than 3 characters",
        placeholder = {
            Text(
                text = stringResource(R.string.enter_username),
                color = Color.LightGray
            )
        }
    )

    CustomField(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        value = state.email,
        onValueChange = { viewModel.processIntent(UserInformationIntent.ValidateEmail(it)) },
        isError = !state.isEmailValid && state.email.isNotBlank(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        errorMessage = stringResource(R.string.invalid_email),
        placeholder = {
            Text(
                text = stringResource(R.string.enter_your_email),
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
        password = state.password,
        onPasswordChange = {
            viewModel.processIntent(UserInformationIntent.ValidatePassword(it))
        },
        placeholder = {
            Text(
                stringResource(R.string.choose_a_password),
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
        password = state.confirmPassword,
        onPasswordChange = {
            viewModel.processIntent(UserInformationIntent.ValidateConfirmPassword(it))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.confirm_password),
                color = Color.LightGray
            )
        }
    )
}
