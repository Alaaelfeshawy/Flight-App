package example.alaa.base.component.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import example.alaa.base.R
import example.alaa.base.component.PastOrPresentSelectableDates

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (selectedStartDate : String ,selectedEndDate: String?) -> Unit,
    onDismiss: () -> Unit,
    pattern: String
) {
    val todayMillis = System.currentTimeMillis()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = todayMillis,
        selectableDates = PastOrPresentSelectableDates
    )
    DatePickerDialog(
        colors = DatePickerDefaults.colors(containerColor = Color.White,),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val selectedDateMillis = datePickerState.selectedDateMillis
                if (selectedDateMillis != null && selectedDateMillis >= todayMillis) {
                    onDateSelected(Utils.convertMillisToDate(selectedDateMillis , pattern) , null)
                }
                onDismiss()
            }) {
                Text(
                    text = stringResource(R.string.ok) ,
                    color = Color.Black
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.cancel) ,
                    color = Color.Black
                )
            }
        }
    ) {
        DatePicker(
            state = datePickerState ,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                todayDateBorderColor = colorResource(R.color.orange),
                todayContentColor = Color.Black,
                selectedDayContentColor = Color.White,
                selectedYearContainerColor = colorResource(R.color.orange),
                selectedDayContainerColor = colorResource(R.color.orange),
                dateTextFieldColors = TextFieldDefaults.colors(
                    focusedIndicatorColor = colorResource(R.color.orange),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black
                ),
                currentYearContentColor = Color.Black,
            ),
        )
    }
}
