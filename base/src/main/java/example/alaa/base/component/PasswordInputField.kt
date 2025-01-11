package example.alaa.base.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import example.alaa.base.R

@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChange: (String) -> Unit,
    placeholder : @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError : Boolean = false,
    colors : TextFieldColors = TextFieldDefaults.colors(
        cursorColor = Color.Black,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = colorResource(example.alaa.base.R.color.orange),
        unfocusedIndicatorColor = Color.LightGray
    ),
    ) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val description = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(
                R.string.show_password
            )

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = description)
            }
        },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        colors = colors,
        placeholder = placeholder,
    )
}