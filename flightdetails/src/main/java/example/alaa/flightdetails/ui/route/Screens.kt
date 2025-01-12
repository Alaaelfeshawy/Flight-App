package example.alaa.flightdetails.ui.route


private enum class Screen {
    FLIGHT_DETAILS,
    SEAT_SELECTION,
}

sealed class NavigationItem(val route: String) {
    data object FlightDetails : NavigationItem(Screen.FLIGHT_DETAILS.name)
    data object SeatSelection : NavigationItem(Screen.SEAT_SELECTION.name)
}