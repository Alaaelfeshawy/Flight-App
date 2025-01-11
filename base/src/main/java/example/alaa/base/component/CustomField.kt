package example.alaa.base.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import example.alaa.base.R

@Composable
fun CustomField(
    modifier: Modifier = Modifier,
    inputTextModifier: Modifier = Modifier,
    value : String,
    onValueChange :(String)->Unit = {},
    isReadOnly : Boolean = false,
    colors : TextFieldColors = TextFieldDefaults.colors(
        cursorColor = Color.Black,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = colorResource(R.color.orange),
        unfocusedIndicatorColor = Color.LightGray,
        errorContainerColor = Color.White,
        errorIndicatorColor = Color.Red,
        disabledContainerColor = Color.White,
        disabledSuffixColor = Color.Black,
        disabledTextColor = Color.Black
    ),
    placeholder : @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError : Boolean = false,
    errorMessage : String?=null,
    title : String?=null,
    enabled : Boolean = true,
    suffix : @Composable (() -> Unit)? = null,
    prefix : @Composable (() -> Unit)? = null,
    ) {

    Column (
        modifier = modifier
    ){
        title?.let {
            Text(
                modifier = Modifier.padding(
                    vertical = 8.dp
                ),
                text = it,
                color = colorResource(R.color.grey),
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange.invoke(it)
            },
            modifier = inputTextModifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = colors,
            readOnly = isReadOnly,
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
            suffix = suffix,
            enabled = enabled,
            prefix = prefix
        )
        if(isError){
            errorMessage?.let {
                Text(
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    ),
                    text = it,
                    color = Color.Red,
                )
            }
        }
    }
}