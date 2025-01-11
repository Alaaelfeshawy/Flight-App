package example.alaa.registration.phonenumber.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.component.CustomField
import example.alaa.registration.R
import example.alaa.registration.phonenumber.PhoneNumberIntent
import example.alaa.registration.phonenumber.PhoneNumberState
import example.alaa.registration.phonenumber.PhoneNumberViewModel

@Composable
fun PhoneNumberComponent(
    modifier: Modifier,
    state: PhoneNumberState,
    viewModel: PhoneNumberViewModel
) {
    Row(
        modifier = modifier.padding(
            horizontal = 8.dp,
            vertical = 24.dp
        ),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(0.2f)
                .height(54.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                modifier = modifier.size(25.dp),
                painter = painterResource(R.drawable.egypt_flag_icon),
                contentDescription = null
            )
            Text(
                "+20",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        CustomField(
            modifier = modifier.padding(start = 14.dp),
            value = state.phoneNumber,
            onValueChange = { phonenumber ->
                viewModel.processIntent(PhoneNumberIntent.ValidatePhoneNumber(phonenumber))
            },
            isError = !state.isValidate && state.phoneNumber.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            errorMessage = stringResource(R.string.invalid_phone_number)
        )
    }
}
