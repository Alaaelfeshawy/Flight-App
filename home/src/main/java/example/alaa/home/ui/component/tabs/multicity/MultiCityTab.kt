package example.alaa.home.ui.component.tabs.multicity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import example.alaa.base.component.PrimaryMainButton
import example.alaa.home.ui.DatePickerActions
import example.alaa.home.ui.component.tabs.components.SingleTripComponent
import example.alaa.home.ui.component.tabs.multicity.components.AddOrRemovingFields


@Composable
fun MultiCityTab(modifier: Modifier = Modifier) {
    val viewModel : MultiCityViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .height(500.dp)
        ){
            items(state.multiCityData.size){ index ->
                Column  {
                    SingleTripComponent(
                        modifier = modifier,
                        origin = state.multiCityData[index].origin.value.orEmpty(),
                        destination = state.multiCityData[index].destination.value.orEmpty(),
                        onExchangeButtonClicked = {
                            viewModel.processIntent(
                                MultiCityTripIntent.ExchangeFields(
                                    destination = state.multiCityData[index].destination.value,
                                    origin = state.multiCityData[index].origin.value,
                                    index = index
                                ))
                        },
                        onDestinationChanged = {
                            viewModel.processIntent(MultiCityTripIntent.ValidateDestinationField(it , index))
                        },
                        onOriginChanged = {
                            viewModel.processIntent(MultiCityTripIntent.ValidateOriginField(it , index))
                         },
                        showDatePicker = state.multiCityData[index].date.showDatePicker,
                        selectedDate = state.multiCityData[index].date.selectedStartDate,
                        onDateSelected = {
                            selectedStartDate , selectedEndDate ->
                            viewModel.handleDatePickerActions(DatePickerActions.OnDateSelected(selectedStartDate , selectedEndDate) , index)
                        },
                        onErrorMessage = {},
                        isSelectRange = false,
                        onClick = {viewModel.handleDatePickerActions(DatePickerActions.OnClick(true),index)},
                        onDismiss = {viewModel.handleDatePickerActions(DatePickerActions.OnClick(false),index)},
                        onAction = {
                            viewModel.handleActions(it , index)
                        },
                        onDialogActions = {
                            viewModel.handleDialogActions(it , index)
                        },
                        onCount = { action , count , passengerIndex ->
                            viewModel.processIntent(
                                MultiCityTripIntent.ValidatePassengerCount(
                                    action, count , passengerIndex , index
                                ))
                        },
                        onConfirm = {
                            viewModel.processIntent(MultiCityTripIntent.ConfirmNumOfPassenger(it , index))
                        },
                        baseTabState = state.multiCityData[index].baseTabState,
                        isOriginError =  state.multiCityData[index].origin.isValid?.not() ?: false,
                        isDepartureError = state.multiCityData[index].date.isValid?.not(),
                        departureErrorMessage = state.multiCityData[index].date.dateErrorMessage,
                        destinationErrorMessage = state.multiCityData[index].destination.errorMessage ,
                        originErrorMessage = state.multiCityData[index].origin.errorMessage,
                        isDestinationError = state.multiCityData[index].destination.isValid?.not() ?: false,
                    )

                    if (state.multiCityData.size.minus(1) != index){
                        HorizontalDivider(
                            modifier = Modifier.padding(14.dp,
                            ),
                            color = Color.LightGray
                        )
                    }
                }
            }
         item {
             Column {
                 AddOrRemovingFields(
                     listSize = state.multiCityData.size,
                     onSecondButtonClick = {
                         viewModel.processIntent(MultiCityTripIntent.RemoveExchangeRateField)
                     },
                     onPrimaryButtonClick = {
                         viewModel.processIntent(MultiCityTripIntent.AddExchangeRateField)
                     },
                 )

                 Spacer(modifier = Modifier.height(12.dp))

                 PrimaryMainButton(
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(12.dp)
                         .height(54.dp),
                     buttonText = "Search",
                     onButtonClick = {
                         viewModel.processIntent(
                             MultiCityTripIntent.Search
                         )
                     },
                 )
             }
         }
    }
}
@Preview
@Composable
fun MultiCityTabPreview(modifier: Modifier = Modifier) {
    MultiCityTab()
}