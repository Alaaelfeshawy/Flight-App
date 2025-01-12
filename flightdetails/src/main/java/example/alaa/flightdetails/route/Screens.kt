package example.alaa.flightdetails.route


private enum class Screen {
    FLIGHT_DETAILS,
}

sealed class NavigationItem(val route: String) {
    data object FlightDetails : NavigationItem(Screen.FLIGHT_DETAILS.name)
}