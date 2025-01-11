package example.alaa.base.component.datepicker

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateSelected: (selectedStartDate : String ,selectedEndDate: String?) -> Unit,
    onErrorMessage : (String) -> Unit,
    onDismiss: () -> Unit,
    pattern : String
) {
    val dateRangePickerState = rememberDateRangePickerState()

    DatePickerDialog(
        colors = DatePickerDefaults.colors(containerColor = Color.White,),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (dateRangePickerState.selectedEndDateMillis != null){
                        onDateSelected(
                            Utils.convertMillisToDate(
                                dateRangePickerState.selectedStartDateMillis , pattern
                            ),
                            Utils.convertMillisToDate(
                                dateRangePickerState.selectedEndDateMillis , pattern),

                            )
                        onDismiss()
                    }else {
                        onErrorMessage.invoke("Please select the other date to confirm")
                    }

                }
            ) {
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
        DateRangePicker(
            state = dateRangePickerState,
            title = {
                Text(
                    modifier = Modifier.padding(12.dp),
                    fontSize = 24.sp,
                    text = stringResource(R.string.select_date_range)
                )
            },
            showModeToggle = false,
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
            )
        )
    }
}