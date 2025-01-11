package example.alaa.registration.landingscreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.SecondButton
import example.alaa.registration.R
import example.alaa.registration.route.NavigationItem

@Composable
fun ButtonsComponent(
    modifier: Modifier,
    navController: NavController?
) {
    Column(
        modifier = modifier.padding(
            vertical = 24.dp
        )
    ) {
        PrimaryMainButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
                .height(58.dp),
            buttonText = stringResource(R.string.sign_up),
            enabled = true,
            onButtonClick = {
                navController?.navigate(NavigationItem.PhoneNumber.route)
            }
        )
        SecondButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                )
                .height(58.dp),
            buttonText = "Login",
            enabled = true,
            onButtonClick = {
                navController?.navigate(
                    NavigationItem.Login.route
                )
            }
        )
    }
}
