package example.alaa.searchresult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import example.alaa.base.component.FlightApp
import example.alaa.base.component.TabBarBackground
import example.alaa.base.component.TripLine
import example.alaa.base.component.model.TripLocation
import example.alaa.searchresult.components.FlightItem

@Composable
fun SearchResult(modifier: Modifier = Modifier, navController: NavHostController?=null) {

    val context = LocalContext.current

    val viewModel = hiltViewModel<SearchResultViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle().value

    FlightApp(
        title = stringResource(R.string.search_result),
        color = colorResource(example.alaa.base.R.color.orange),
        backArrowColor = Color.White,
        navigationButtonClicked = {
            navController?.popBackStack()
        }
    ){
        Column {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(example.alaa.base.R.color.orange))
            ){
                TripLine(
                    modifier = Modifier ,
                    startLocation = TripLocation(
                        title = "Temale",
                        subtitle = "Temale International Airport",
                    ),
                    image = R.drawable.airplane_1,
                    imageInfo = "01h 45m",
                    endLocation = TripLocation(
                        title = "Kumasi",
                        subtitle = "Kumasi International Airport",
                    ),
                 )
                TabBarBackground()
            }

            LazyRow(
                modifier = Modifier.padding(horizontal = 12.dp)
            ){
               items(state.tabs.size){ index->
                   val isSelected = index == state.selectedTab

                   Button(
                       modifier = Modifier.padding(horizontal = 6.dp),
                       onClick = {
                           viewModel.processIntent(SearchResultIntent.SetSelected(index))
                       },
                       colors = ButtonDefaults.buttonColors(
                           containerColor = if (isSelected) colorResource(example.alaa.base.R.color.orange) else Color.White
                       ),
                       elevation = ButtonDefaults.elevatedButtonElevation(
                           defaultElevation = 5.dp
                       )
                       ) {
                       Text(
                           text = state.tabs[index],
                           color = if (isSelected) Color.White else Color.Gray
                       )
                   }
               }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                items(1){
                    FlightItem(modifier,context)
                }
            }
        }

    }
}

@Preview
@Composable
fun SearchResultPreview(){
    SearchResult()
}