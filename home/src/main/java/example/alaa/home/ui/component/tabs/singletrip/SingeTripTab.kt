package example.alaa.home.ui.component.tabs.singletrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import example.alaa.base.Constants.SEARCH_MODEL
import example.alaa.base.component.PrimaryMainButton
import example.alaa.home.ui.DatePickerActions
import example.alaa.home.ui.component.tabs.TabEvent
import example.alaa.home.ui.component.tabs.components.SingleTripComponent
import example.alaa.home.ui.model.SearchModel
import example.alaa.home.ui.model.TripType
import example.alaa.searchresult.SearchResultActivity


@Composable
fun SingeTripTab(modifier: Modifier = Modifier) {
    val viewModel : SingleTripViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.processIntent(SingleTripIntent.SetTripType(TripType.SINGLE_TRIP))
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect{
            when(it){
                is TabEvent.NavigateToSearch<*> -> {
                    navigateToSearchScreen(context , it.searchDate as SearchModel)
                }
            }
        }
    }

    Column {
        SingleTripComponent(
            modifier = modifier,
            origin = state.singleTripState.origin.value.orEmpty(),
            destination = state.singleTripState.destination.value.orEmpty(),
            onExchangeButtonClicked = {
                viewModel.processIntent(
                    SingleTripIntent.ExchangeFields(
                        origin = state.singleTripState.origin.value,
                        destination = state.singleTripState.destination.value,
                    ))
            },
            onDestinationChanged = {
                viewModel.processIntent(SingleTripIntent.ValidateDestinationField(it))
            },
            onOriginChanged = {
                viewModel.processIntent(SingleTripIntent.ValidateOriginField(it))
            },
            showDatePicker = state.singleTripState.date.showDatePicker,
            selectedDate = state.singleTripState.date.selectedStartDate,
            onDateSelected = {
                    selectedStartDate , selectedEndDate ->
            viewModel.handleDatePickerActions(DatePickerActions.OnDateSelected( selectedStartDate , selectedEndDate))
            },
            isSelectRange = false,
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
            baseTabState = state.singleTripState.baseTabState,
            onErrorMessage = {},
            destinationErrorMessage = state.singleTripState.destination.errorMessage ,
            originErrorMessage = state.singleTripState.origin.errorMessage,
            isDestinationError = state.singleTripState.destination.isValid?.not() ?: false,
            isOriginError = state.singleTripState.origin.isValid?.not() ?: false,
            isDepartureError = state.singleTripState.date.isValid?.not() ?: false,
            departureErrorMessage = state.singleTripState.date.dateErrorMessage,
            )

        PrimaryMainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .height(56.dp),
            buttonText = "Search",
            onButtonClick = {
                viewModel.processIntent(
                    SingleTripIntent.Search
                )
            },
        )
    }

}
private fun navigateToSearchScreen(context: Context , searchModel :SearchModel){
    context.startActivity(Intent( context, SearchResultActivity::class.java).apply {
        val mBundle = Bundle()
        mBundle.putParcelable(SEARCH_MODEL,searchModel)
        putExtras(mBundle)
    })
}
@Preview
@Composable
fun OneWayPreview(){
    SingeTripTab()
}