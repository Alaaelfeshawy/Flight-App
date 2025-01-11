package example.alaa.registration.landingscreen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import example.alaa.registration.R


@Composable
fun FooterComponent(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = modifier
                .padding(
                    20.dp
                )
                .align(alignment = Alignment.BottomCenter),
            text = buildAnnotatedString {
                append(stringResource(R.string.by_continuing_you_accept_our))

                withStyle(
                    style = SpanStyle(
                        color = colorResource(example.alaa.base.R.color.orange),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(stringResource(R.string.terms_of_service))
                }

                append(stringResource(R.string.also_learn_how_we_process_your_data_in_our))

                withStyle(
                    style = SpanStyle(
                        color = colorResource(example.alaa.base.R.color.orange),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp
                    )
                ) {
                    append(stringResource(R.string.privacy_policy))
                }
                append(stringResource(R.string.and))

                withStyle(
                    style = SpanStyle(
                        color = colorResource(example.alaa.base.R.color.orange),
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp
                    )
                ) {
                    append(stringResource(R.string.cookies_policy))
                }
            },
            style = TextStyle(
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
            color = colorResource(example.alaa.base.R.color.grey)
        )
    }
}