package example.alaa.home.ui.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SearchModel(
    val tripType: TripType,
    val origin : String,
    val destination : String,
    val selectedStartDate : String,
    val selectedEndDate : String?=null,
    val selectedPassenger: PassengerUiModel,
    val selectedClass : String,
) : Parcelable