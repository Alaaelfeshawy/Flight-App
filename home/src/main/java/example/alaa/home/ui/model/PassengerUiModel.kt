package example.alaa.home.ui.model

import androidx.annotation.Keep

@Keep
data class PassengerUiModel (
    val passengerType : String,
    val rangePassenger : String,
    val numberOfTravellers : Int,
)