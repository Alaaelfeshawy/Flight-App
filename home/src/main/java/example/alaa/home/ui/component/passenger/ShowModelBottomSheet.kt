package example.alaa.home.ui.component.passenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenSubtitle
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.model.PassengerUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowModelBottomSheet(
    isBottomSheetExpanded: Boolean,
    list : List<PassengerUiModel>,
    onConfirm: (Int) -> Unit,
    onCount: (Action, Int, Int) -> Unit,
    isTotalCountValid : Boolean?,
    errorMessage :String,
) {
    if (isBottomSheetExpanded){
        ModalBottomSheet(
            onDismissRequest = {},
            scrimColor = Color.Black.copy(alpha = 0.32f),
            containerColor = Color.White,
            sheetState = rememberModalBottomSheetState(
                confirmValueChange = { it != SheetValue.Hidden },
                skipPartiallyExpanded = true
            ),
        ) {
            ModalBottomSheetContent(
                list,
                onCount,
                isTotalCountValid,
                errorMessage,
                onConfirm,
            )
        }
    }

}

@Composable
private fun ModalBottomSheetContent(
    list: List<PassengerUiModel>,
    onCount: (Action, Int, Int) -> Unit,
    isTotalCountValid: Boolean?,
    errorMessage :String,
    onConfirm: (Int) -> Unit
) {
    var selectedIndex = -1

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            itemsIndexed(list) { index, passenger ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier
                            .padding(
                                start = 6.dp
                            )
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = passenger.passengerType,
                            style = TextStyle(
                                fontSize = 18.sp
                            ),
                            maxLines = 2

                        )
                        ScreenSubtitle(
                            modifier = Modifier.padding(4.dp),
                            text = passenger.rangePassenger
                        )
                    }

                    Count(
                        modifier = Modifier.weight(0.5f),
                        count = passenger.numberOfTravellers,
                        onClick = { action, count ->
                            onCount.invoke(action, count, index)
                            selectedIndex = index
                        }
                    )
                }
            }
            item {
                if (isTotalCountValid == false) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = errorMessage,
                        color = Color.Red
                    )
                }
            }
            item {
                PrimaryMainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    buttonText = "Confirm",
                    enabled = isTotalCountValid == true,
                    onButtonClick = {
                        if (isTotalCountValid == true) {
                            onConfirm.invoke(selectedIndex)
                        }

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PopUpDialogPreview(){
    ModalBottomSheetContent(
        list = listOf(
            PassengerUiModel(
                "Adults",
                "18-64",
                1,
            ),
            PassengerUiModel(
                "Seniors",
                "over 65",
                0,

                ),
            PassengerUiModel(
                "Youth",
                "12-17",
                0,
                ),
            PassengerUiModel(
                "Children",
                "2-11",
                0,

                ),
            PassengerUiModel(
                "Toddlers in own seat",
                "under 2",
                0,

                ),
            PassengerUiModel(
                "Infants on lap",
                "under 2",
                0,
            ),

            ),
        onConfirm = {},
        onCount = {_,_ ,_->},
        isTotalCountValid = true,
        errorMessage = ""
    )
}
