package example.alaa.registration.route

private enum class Screen {
    LANDING,
    OTP,
    PHONE_NUMBER,
    SUCCESS,
    USER_INFORMATION,
    LOGIN
}

sealed class NavigationItem(val route: String) {
    data object Landing : NavigationItem(Screen.LANDING.name)
    data object Otp : NavigationItem(Screen.OTP.name)
    data object PhoneNumber : NavigationItem(Screen.PHONE_NUMBER.name)
    data object Success : NavigationItem(Screen.SUCCESS.name)
    data object UserInformation : NavigationItem(Screen.USER_INFORMATION.name)
    data object Login : NavigationItem(Screen.LOGIN.name)
}