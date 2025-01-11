package example.alaa.home.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.Keep

@Keep
data class OffersUiModel(
   @DrawableRes var logo : Int,
    var offer : Double,
    var cardName : String,
    var backgroundColor : Int,
)