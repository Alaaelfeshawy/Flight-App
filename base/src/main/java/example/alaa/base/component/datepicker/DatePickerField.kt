package example.alaa.base.component.datepicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import example.alaa.base.R
import example.alaa.base.component.CustomField

@Composable
fun DatePickerField(
    showModal: Boolean,
    isSelectRange: Boolean,
    selectedDate: String,
    onDateSelected: (selectedStartDate: String, selectedEndDate: String?) -> Unit,
    onErrorMessage: (String) -> Unit = {},
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    pattern: String = "EEE, MMMM d",
    isDepartureError: Boolean?,
    departureErrorMessage: String?
) {

    CustomField(
        value = selectedDate,
        title = stringResource(R.string.departure),
        placeholder = { Text("Tue,May 06") },
        prefix = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                tint = Color.Black
            )
        },
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clickable {
                onClick.invoke()
            },
        enabled = false,
        isError = isDepartureError == true,
        errorMessage = departureErrorMessage
    )

    if (showModal && !isSelectRange) {
        DatePickerModal(
            onDateSelected = onDateSelected,
            onDismiss = {onDismiss.invoke()},
            pattern = pattern
        )
    }else if(showModal){
        DateRangePickerModal(
            onDateSelected = onDateSelected,
            onDismiss = {onDismiss.invoke()},
            onErrorMessage = onErrorMessage,
            pattern = pattern
        )
    }
}

@Preview
@Composable
fun DatePickerModalPreview(){
    DatePickerField(
        showModal = true,
        isSelectRange = false,
        selectedDate = "",
        onDateSelected = {_,_ -> },
        onClick = {},
        onDismiss = {},
        isDepartureError = true,
        departureErrorMessage = "couldn't be emoty"
    )
}