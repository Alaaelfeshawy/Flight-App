package example.alaa.flightdetails.ui.seatselection.model

data class SeatModel(
    val row: Int,
    val column: String,
    val status: SeatStatus = SeatStatus.AVAILABLE,
    val price: Double = 0.0,
)

enum class SeatStatus {
    AVAILABLE,
    SELECTED,
    UNAVAILABLE
}