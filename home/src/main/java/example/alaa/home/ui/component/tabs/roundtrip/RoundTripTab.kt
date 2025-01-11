package example.alaa.home.ui.component.tabs.roundtrip

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import example.alaa.base.component.PrimaryMainButton
import example.alaa.home.ui.DatePickerActions
import example.alaa.home.ui.component.tabs.components.SingleTripComponent
import example.alaa.home.ui.component.tabs.singletrip.SingleTripIntent
import example.alaa.home.ui.component.tabs.singletrip.SingleTripViewModel
import example.alaa.home.ui.model.TripType

@Composable
fun RoundTripTab(modifier: Modifier = Modifier) {
    val viewModel : SingleTripViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.processIntent(SingleTripIntent.SetTripType(TripType.ROUNDED_TRIP))
    }
    Column {
        SingleTripComponent(
            modifier = modifier,
            origin = state.roundedTripState.origin.value.orEmpty(),
            destination = state.roundedTripState.destination.value.orEmpty(),
            onExchangeButtonClicked = {
                viewModel.processIntent(
                    SingleTripIntent.ExchangeFields(
                        destination = state.roundedTripState.destination.value,
                        origin = state.roundedTripState.origin.value,
                    ))
            },
            onDestinationChanged = {
                viewModel.processIntent(SingleTripIntent.ValidateDestinationField(it))
            },
            onOriginChanged = {
                viewModel.processIntent(SingleTripIntent.ValidateOriginField(it))
            },
            showDatePicker = state.roundedTripState.date.showDatePicker,
            selectedDate = getSelectedDate(
                selectedStartDate = state.roundedTripState.date.selectedStartDate,
                selectedEndDate = state.roundedTripState.date.selectedEndDate,
            ),
            onDateSelected = {
                    selectedStartDate , selectedEndDate ->
            viewModel.handleDatePickerActions(DatePickerActions.OnDateSelected(selectedStartDate , selectedEndDate))
            },
            onErrorMessage = {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            },
            isSelectRange = true,
            onClick = {viewModel.handleDatePickerActions(DatePickerActions.OnClick(true))},
            onDismiss = {viewModel.handleDatePickerActions(DatePickerActions.OnClick(false))},
            onAction = {
                viewModel.handleActions(it)
            },
            onDialogActions = {
                viewModel.handleDialogActions(it)
            },
            onCount = { action , count , index ->
                viewModel.processIntent(
                    SingleTripIntent.ValidatePassengerCount(
                        action, count , index
                    ))
            },
            onConfirm = {
                viewModel.processIntent(SingleTripIntent.ConfirmNumOfPassenger(it))
            },
            baseTabState = state.roundedTripState.baseTabState,
            destinationErrorMessage = state.roundedTripState.destination.errorMessage ,
            originErrorMessage = state.roundedTripState.origin.errorMessage,
            isDestinationError = state.roundedTripState.destination.isValid?.not() ?: false,
            isOriginError = state.roundedTripState.origin.isValid?.not() ?: false,
            isDepartureError = state.roundedTripState.date.isValid?.not() ?: false,
            departureErrorMessage = state.roundedTripState.date.dateErrorMessage,
        )
        PrimaryMainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(54.dp),
            buttonText = "Search",
            onButtonClick = {
                viewModel.processIntent(
                    SingleTripIntent.Search
                )
            },
        )
    }

}

fun getSelectedDate(selectedStartDate: String, selectedEndDate: String?): String {
    return when {
        selectedEndDate == null && selectedStartDate.isNotEmpty() -> selectedStartDate
        selectedStartDate.isNotEmpty() && selectedEndDate != null -> "from $selectedStartDate to $selectedEndDate"
        else -> ""
    }
}
