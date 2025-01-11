package example.alaa.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import example.alaa.base.R
import example.alaa.base.component.FlightApp
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.home.ui.component.OffersSection
import example.alaa.home.ui.component.SearchForm

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController? = null) {
    val viewModel : HomeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    FlightApp(
        modifier = modifier.verticalScroll(rememberScrollState()),
        showNavigationButton = false
    ){
        ScreenHeader(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = 20.dp,
                vertical = 10.dp,
            ),
            text = "Hello there..!",
            color = colorResource(R.color.grey)
        )
        ScreenSubtitle(
            modifier = Modifier.fillMaxWidth().padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
            text = "MacRaymond",
            color = Color.Black
        )
        SearchForm(
            state = state,
            onTabSelected = {
                viewModel.processIntent(HomeIntent.SetSelectedTab(it))
            },
        )

        OffersSection(
            modifier = Modifier.padding(top = 8.dp),
            state = state
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}