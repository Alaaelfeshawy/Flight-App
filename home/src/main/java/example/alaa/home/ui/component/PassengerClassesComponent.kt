package example.alaa.home.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import example.alaa.base.component.DropDownComponent
import example.alaa.home.R
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.DropDownListActions
import example.alaa.home.ui.PassengerDialogActions
import example.alaa.home.ui.component.passenger.PassengerComponent
import example.alaa.home.ui.component.tabs.BaseTabState

@Composable
fun PassengerClassesComponent(
    state : BaseTabState,
    onAction: (DropDownListActions) -> Unit,
    onDialogActions: (PassengerDialogActions) -> Unit,
    onCount: (Action, Int, Int) -> Unit,
    onConfirm: (Int) -> Unit,
    ){
    Column {
        Row {
            PassengerComponent(
                modifier = Modifier.weight(1f),
                onClick = {
                    onDialogActions(PassengerDialogActions.OnClick)
                },
                onCount = onCount,
                onConfirm = onConfirm,
                numOfPassengers = state.numOfPassengers,
                passengers = state.passengers,
                selectedPassengerIndex = state.selectedPassengerIndex,
                isBottomSheetExpanded = state.isPassengerBottomSheetExpanded,
                isTotalPassengerCountValid = state.isTotalPassengerCountValid ,
                invalidTotalPassengerCountErrorMessage = state.invalidTotalPassengerCountErrorMessage,
            )

            DropDownComponent(
                modifier = Modifier.weight(1f),
                onDismissRequest = {
                    onAction(DropDownListActions.OnDismissRequest)
                },
                onDropDownClicked = {
                    onAction(DropDownListActions.OnDropDownClicked)
                },
                onItemSelected = {
                    onAction(DropDownListActions.OnItemSelected(item = it))
                },
                list = state.planeClasses ,
                selectedItemPosition = state.selectedClassPosition,
                isDropDownExpanded = state.isClassDropDownExpanded,
                title = stringResource(R.string.class_label)
            )
        }
    }
}