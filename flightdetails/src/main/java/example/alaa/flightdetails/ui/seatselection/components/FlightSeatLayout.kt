package example.alaa.flightdetails.ui.seatselection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import example.alaa.flightdetails.ui.seatselection.model.SeatModel

@Composable
fun FlightSeatLayout(seats : List<SeatModel?>,
                     columns : List<String> ,
                     rows : Int ,
                     onSeatClicked : (SeatModel?) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()){
            for (column in columns.indices){
                Text(columns[column] , modifier = Modifier.weight(1f).padding(horizontal = 12.dp) , textAlign = TextAlign.Center)
            }
        }
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text((row.toString()) , textAlign = TextAlign.Center , modifier = Modifier.align(
                    Alignment.CenterVertically))

                for (column in columns.indices) {
                    val seat = seats.find { it?.row == row.plus(1) && it.column == columns[column] }
                    if (seat != null) {
                        Seat(
                            seat = seat,
                            onClick = onSeatClicked
                        )
                    }
                }
            }
        }
    }
}

