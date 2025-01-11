package example.alaa.home.ui.component.tabs.multicity

import example.alaa.base.ViewIntent
import example.alaa.base.ViewState
import example.alaa.home.ui.model.Action
import example.alaa.home.ui.component.tabs.singletrip.SingleTripState


data class MultiCityTripState(
    val multiCityData : MutableList<SingleTripState>,
    val allFormsAreValid : Boolean = false
) : ViewState


sealed class MultiCityTripIntent : ViewIntent {
    data object Search : MultiCityTripIntent()
    data class ValidateOriginField(val value : String, val index : Int) : MultiCityTripIntent()
    data class ValidateDestinationField(val value : String, val index : Int) : MultiCityTripIntent()
    data class ExchangeFields( val origin : String?  , val destination : String? , val index : Int) : MultiCityTripIntent()
    data class ValidatePassengerCount(val action: Action, val count: Int, val passengerIndex: Int, val tripIndex : Int) : MultiCityTripIntent()
    data class ConfirmNumOfPassenger(val count: Int ,  val tripIndex : Int) : MultiCityTripIntent()
    data object AddExchangeRateField : MultiCityTripIntent()
    data object  RemoveExchangeRateField : MultiCityTripIntent()
}