package example.alaa.onboarding.route

private enum class Screen {
    SPLASH,
    ONBOARDING,
}

sealed class NavigationItem(val route: String) {
    data object Splash : NavigationItem(Screen.SPLASH.name)
    data object Onboarding : NavigationItem(Screen.ONBOARDING.name)
}