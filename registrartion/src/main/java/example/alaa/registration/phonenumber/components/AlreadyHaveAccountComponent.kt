package example.alaa.registration.phonenumber.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import example.alaa.registration.R
import example.alaa.registration.route.NavigationItem

@Composable
fun AlreadyHaveAccountComponent(modifier: Modifier, navController: NavController?){
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = colorResource(example.alaa.base.R.color.grey),
            )
        ) {
            append(stringResource(R.string.already_have_an_account))
        }

        pushStringAnnotation(tag = "login", annotation = "login") // Add annotation for click handling
        withStyle(
            style = SpanStyle(
                color = colorResource(example.alaa.base.R.color.orange),
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(R.string.login))
        }
        pop() // Remove annotation
    }
    Text(
        text = annotatedString,
        style = TextStyle(
            fontSize = 14.sp
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    navController?.navigate(NavigationItem.Login.route)
                },
                indication = null, // Remove the ripple effect
                interactionSource = remember { MutableInteractionSource() } // Required when indication is null
            )
            .padding(vertical = 24.dp),
        textAlign = TextAlign.Center,
        letterSpacing = 1.sp,

        )
}