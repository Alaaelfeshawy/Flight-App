package example.alaa.home.ui.model

import androidx.annotation.Keep

@Keep
data class SearchModel(
    val tripType: TripType,
    val origin : String,
    val destination : String,
    val selectedStartDate : String,
    val selectedEndDate : String?=null,
    val selectedPassenger: PassengerUiModel,
    val selectedClass : String,
)