package example.alaa.home.ui.component.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import example.alaa.home.ui.HomeState
import example.alaa.home.ui.component.tabs.multicity.MultiCityTab
import example.alaa.home.ui.component.tabs.roundtrip.RoundTripTab
import example.alaa.home.ui.component.tabs.singletrip.SingeTripTab

@Composable
fun TabScreen(
    modifier : Modifier = Modifier,
    state: HomeState,
    onTabSelected : (Int) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier.padding( 14.dp),
            shape = RoundedCornerShape(24.dp)
        ){
            TabRow(
                modifier = Modifier.height(32.dp),
                selectedTabIndex = state.selectedTab,
                divider = {},
                indicator = {}
            ) {
                state.tabs.forEachIndexed { index, title ->
                    Tab(
                        modifier = Modifier
                            .background(
                                if (state.selectedTab != index) colorResource(example.alaa.base.R.color.white_02) else colorResource(example.alaa.base.R.color.orange),
                            ),
                        text = { Text(title) },
                        selected = state.selectedTab == index,
                        onClick = { onTabSelected.invoke(index) },
                        unselectedContentColor = colorResource(example.alaa.base.R.color.grey),
                        selectedContentColor = colorResource(example.alaa.base.R.color.white)
                    )
                }
            }
        }
        when (state.selectedTab) {
            0 -> SingeTripTab()

            1 -> RoundTripTab()

            2 -> MultiCityTab(
                modifier = modifier
            )
        }
    }
}