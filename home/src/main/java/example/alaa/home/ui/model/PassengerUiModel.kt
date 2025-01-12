package example.alaa.home.ui.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class PassengerUiModel (
    val passengerType : String,
    val rangePassenger : String,
    val numberOfTravellers : Int,
) : Parcelable