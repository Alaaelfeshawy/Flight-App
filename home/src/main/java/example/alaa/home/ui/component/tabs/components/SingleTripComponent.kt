package example.alaa.home.ui.component.tabs.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.alaa.base.component.datepicker.DatePickerField
import example.alaa.home.ui.DropDownListActions
import example.alaa.home.ui.PassengerDialogActions
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.component.ExchangeFields
import example.alaa.home.ui.component.PassengerClassesComponent
import example.alaa.home.ui.component.tabs.BaseTabState

@Composable
fun SingleTripComponent(
    modifier: Modifier = Modifier,
    origin: String,
    destination: String,
    onExchangeButtonClicked: () -> Unit,
    onDestinationChanged: (String) -> Unit,
    onOriginChanged: (String) -> Unit,
    showDatePicker: Boolean,
    selectedDate: String,
    onDateSelected: (selectedStartDate: String, selectedEndDate: String?) -> Unit,
    onErrorMessage: (String) -> Unit,
    isSelectRange: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onAction: (DropDownListActions) -> Unit,
    onDialogActions: (PassengerDialogActions) -> Unit,
    onCount: (action: Action, count: Int, index: Int) -> Unit,
    onConfirm: (Int) -> Unit,
    baseTabState: BaseTabState,
    isOriginError: Boolean,
    isDestinationError: Boolean,
    originErrorMessage: String? = null,
    destinationErrorMessage: String? = null,
    isDepartureError: Boolean?,
    departureErrorMessage: String?,
    ) {

    Column {
        ExchangeFields(
            modifier = modifier.padding(horizontal = 14.dp),
            origin = origin,
            destination = destination,
            onExchangeButtonClicked = onExchangeButtonClicked,
            onOriginChanged = onOriginChanged,
            onDestinationChanged = onDestinationChanged,
            isOriginError = isOriginError,
            isDestinationError = isDestinationError,
            originErrorMessage = originErrorMessage,
            destinationErrorMessage = destinationErrorMessage
        )

        DatePickerField(
            showModal = showDatePicker,
            selectedDate = selectedDate,
            onDateSelected = onDateSelected,
            isSelectRange = isSelectRange,
            onClick = onClick,
            onDismiss = onDismiss,
            onErrorMessage = onErrorMessage,
            isDepartureError = isDepartureError,
            departureErrorMessage = departureErrorMessage,

        )

        PassengerClassesComponent(
            onAction = onAction,
            onDialogActions = onDialogActions,
            onCount = onCount,
            onConfirm = onConfirm,
            state = baseTabState
        )
    }
}