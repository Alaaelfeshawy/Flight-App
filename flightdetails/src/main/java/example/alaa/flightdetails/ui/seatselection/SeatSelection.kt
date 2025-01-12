package example.alaa.flightdetails.ui.seatselection

import example.alaa.base.ViewEvent
import example.alaa.base.ViewIntent
import example.alaa.base.ViewState
import example.alaa.flightdetails.ui.seatselection.model.SeatModel

data class SeatSelectionState (
    val seats: List<SeatModel?>,
    val columns : List<String> = listOf("A", "B", "C", "D", "E", "F"),
    val isConfirmSeatsValid : Boolean?=null,
    val errorMessage : String?= null
) : ViewState

sealed class SeatSelectionIntent  : ViewIntent{
    data class SetSeat(val seatModel: SeatModel?) : SeatSelectionIntent()
    data object ConfirmSeats : SeatSelectionIntent()
}

sealed class SeatSelectionEvents : ViewEvent{
    data class NavigateToPayment(val seatModel : List<SeatModel?>) : SeatSelectionEvents()
}