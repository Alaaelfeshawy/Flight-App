package example.alaa.home.ui.component.passenger

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.alaa.base.component.CustomField
import example.alaa.base.component.ScreenSubtitle
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.model.PassengerUiModel

@Composable
fun PassengerComponent(
    modifier: Modifier = Modifier,
    numOfPassengers : Int,
    passengers: List<PassengerUiModel>,
    selectedPassengerIndex : Int,
    isBottomSheetExpanded : Boolean,
    isTotalPassengerCountValid : Boolean,
    invalidTotalPassengerCountErrorMessage : String,
    onClick : () -> Unit,
    onCount: (Action, Int, Int) -> Unit,
    onConfirm: (Int) -> Unit,
    ) {
        Column(modifier = modifier
            .padding(
                horizontal = 14.dp
            )) {
            Row {
                CustomField(
                    inputTextModifier = Modifier.clickable {
                        onClick.invoke()
                    },
                    value = "$numOfPassengers Traveller",
                    title = "Passenger",
                    isReadOnly = true,
                    suffix = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    enabled = false
                )
                ScreenSubtitle(
                    text = passengers[selectedPassengerIndex].rangePassenger
                )
            }
            ShowModelBottomSheet(
                isBottomSheetExpanded = isBottomSheetExpanded,
                list = passengers,
                onCount = onCount,
                isTotalCountValid = isTotalPassengerCountValid,
                errorMessage = invalidTotalPassengerCountErrorMessage,
                onConfirm = onConfirm
            )
        }
}


