package example.alaa.flightdetails.ui.seatselection

import dagger.hilt.android.lifecycle.HiltViewModel
import example.alaa.base.BaseViewModel
import example.alaa.flightdetails.ui.seatselection.model.SeatModel
import example.alaa.flightdetails.ui.seatselection.model.SeatStatus
import javax.inject.Inject

@HiltViewModel
class SeatSelectionViewModel @Inject constructor() : BaseViewModel<
        SeatSelectionIntent, SeatSelectionState, SeatSelectionEvents>() {

    override val initialState: SeatSelectionState
        get() = SeatSelectionState(
            seats = generateSeats()
        )

    private fun generateSeats(): List<SeatModel> {
        val rows = 'A'..'F'
        val numbers = 1..9
        val seats = mutableListOf<SeatModel>()
        for (row in rows) {
            for (number in numbers) {
                val status = if (row == 'A' && number == 1) SeatStatus.UNAVAILABLE else SeatStatus.AVAILABLE
                seats.add(SeatModel(number, row.toString(), status))
            }
        }
        return seats
    }

    override fun handleIntent(intent: SeatSelectionIntent) {
        when (intent) {
            is SeatSelectionIntent.SetSeat -> setState {
                copy(seats = seats.map { seat ->
                    if (seat == intent.seatModel) {
                        when (seat?.status) {
                            SeatStatus.AVAILABLE -> seat.copy(status = SeatStatus.SELECTED)
                            SeatStatus.SELECTED -> seat.copy(status = SeatStatus.AVAILABLE)
                            else -> seat
                        }
                    } else {
                        seat
                    }
                })
            }

            SeatSelectionIntent.ConfirmSeats -> {
                val selectedSeats = state.value.seats.filter { it?.status == SeatStatus.SELECTED }
                val numberOfSeats = selectedSeats.size

                val errorMessage = when {
                    numberOfSeats == 0 -> "please choose your seats"
                    numberOfSeats > 16 -> "You can select a maximum of 16 seats"
                    else -> null
                }

                setState {copy(errorMessage = errorMessage) }

                if (errorMessage == null) {
                    sendEvent(SeatSelectionEvents.NavigateToPayment(selectedSeats))
                }
            }
        }
    }
}