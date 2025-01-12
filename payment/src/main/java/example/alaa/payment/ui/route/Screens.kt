package example.alaa.payment.ui.route


private enum class Screen {
    PAYMENT,
    BOARDING_PASS
}

sealed class NavigationItem(val route: String) {
    data object Payment : NavigationItem(Screen.PAYMENT.name)
    data object BoardingPass : NavigationItem(Screen.BOARDING_PASS.name)
}