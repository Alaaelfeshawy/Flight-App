package example.alaa.searchresult.route

private enum class Screen {
    SEARCH_RESULT,
}

sealed class NavigationItem(val route: String) {
    data object SearchResult : NavigationItem(Screen.SEARCH_RESULT.name)
}