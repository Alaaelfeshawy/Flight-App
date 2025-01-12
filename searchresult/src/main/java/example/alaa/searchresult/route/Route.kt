package example.alaa.searchresult.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import example.alaa.searchresult.SearchResult

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.SearchResult.route,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavigationItem.SearchResult.route) {
            SearchResult(modifier , navController)
        }
    }
}