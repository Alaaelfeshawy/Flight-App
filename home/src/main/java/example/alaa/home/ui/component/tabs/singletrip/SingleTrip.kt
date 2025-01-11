package example.alaa.home.ui.component.tabs.singletrip

import example.alaa.base.ViewIntent
import example.alaa.base.ViewState
import example.alaa.home.ui.component.tabs.BaseTabState
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.model.DatePickerData
import example.alaa.home.ui.model.FieldValidation
import example.alaa.home.ui.model.TripType

data class TabState(
    val tripType: TripType = TripType.SINGLE_TRIP,
    val singleTripState: SingleTripState,
    val roundedTripState: SingleTripState,
    val mutliCityTripState: List<SingleTripState>,

    ): ViewState

data class SingleTripState(
    val origin : FieldValidation = FieldValidation(),
    val destination : FieldValidation = FieldValidation(),
    val baseTabState: BaseTabState = BaseTabState(),
    val date: DatePickerData = DatePickerData(),
    val isFormValid : Boolean = false,
)


sealed class SingleTripIntent : ViewIntent {
    data object Search : SingleTripIntent()
    data class ValidateDestinationField(val value : String) : SingleTripIntent()
    data class ValidateOriginField(val value : String) : SingleTripIntent()
    data class ExchangeFields(val origin : String?, val destination : String?) : SingleTripIntent()
    data class ValidatePassengerCount(val action: Action, val count: Int, val index: Int) : SingleTripIntent()
    data class ConfirmNumOfPassenger(val count: Int) : SingleTripIntent()
    data class SetTripType(val tripType: TripType) : SingleTripIntent()
}