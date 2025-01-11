package example.alaa.home.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.home.R
import example.alaa.home.ui.HomeState
import example.alaa.home.ui.component.tabs.TabScreen

@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
               onTabSelected: (Int) -> Unit= {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(
                horizontal = 12.dp,
            ),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 20.sp,
                text = stringResource(R.string.book_your_flight),
                textAlign = TextAlign.Center
            )

            TabScreen(
                modifier = modifier,
                state = state,
                onTabSelected = onTabSelected,
                )

            }

        }
}



@Preview
@Composable
fun SearchFormPreview(){
    SearchForm()
}

