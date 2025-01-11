package example.alaa.home.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import example.alaa.base.R
import example.alaa.base.component.CustomField

@Composable
fun ExchangeFields(modifier: Modifier = Modifier,
                   destination: String?,
                   origin: String?,
                   isOriginError: Boolean,
                   isDestinationError: Boolean,
                   onOriginChanged : (String)->Unit,
                   onDestinationChanged : (String)->Unit,
                   onExchangeButtonClicked : ()-> Unit,
                   originErrorMessage : String?=null,
                   destinationErrorMessage: String?=null,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (fromField, toField, exchangeButton) = createRefs()

        Column(modifier = Modifier
            .constrainAs(fromField) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomField(
                    value = origin.orEmpty(),
                    onValueChange = {
                        onOriginChanged.invoke(it)
                    },
                    title = stringResource(example.alaa.home.R.string.from),
                    isError = isOriginError,
                    errorMessage = originErrorMessage
                )
            }
        }

        // To Field
        Column(modifier = Modifier
            .constrainAs(toField) {
                top.linkTo(fromField.bottom, margin = (-6).dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomField(
                    value = destination.orEmpty(),
                    onValueChange = {onDestinationChanged.invoke(it)},
                    title = stringResource(example.alaa.home.R.string.to),
                    isError = isDestinationError,
                    errorMessage = destinationErrorMessage
                  )
            }
        }
        // Exchange Button Overlay
        Surface(
            modifier = Modifier
                .size(46.dp)
                .constrainAs(exchangeButton) {
                    top.linkTo(toField.top, margin = (-6).dp) // Align with the top of toField
                    end.linkTo(toField.end, margin = 32.dp) // Align to the end of toField
                },
            shape = CircleShape,
            color = colorResource(R.color.orange),
        ) {
            IconButton(
                onClick = {
                    onExchangeButtonClicked.invoke()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.SwapVert,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun ExchangeFieldsPreview(){
    ExchangeFields(
        origin = "Cairo",
        destination = "Sharm",
        onOriginChanged = {},
        onDestinationChanged = {},
        isOriginError = true,
        isDestinationError = true,
        destinationErrorMessage = "",
        originErrorMessage = "",
        onExchangeButtonClicked = {}
    )
}