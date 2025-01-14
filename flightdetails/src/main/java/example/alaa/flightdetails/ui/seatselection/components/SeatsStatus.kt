package example.alaa.flightdetails.ui.seatselection.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import example.alaa.searchresult.R

@Composable
fun SeatsStatus() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SeatStatus(color = Color.Gray, text = stringResource(R.string.reversed))
            SeatStatus(
                color = Color.White, text = stringResource(R.string.available),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = colorResource(example.alaa.base.R.color.orange),
                    shape = CircleShape
                )
            )
            SeatStatus(
                text = stringResource(R.string.selected),
                color = colorResource(example.alaa.base.R.color.orange),
            )
        }
    }
}
