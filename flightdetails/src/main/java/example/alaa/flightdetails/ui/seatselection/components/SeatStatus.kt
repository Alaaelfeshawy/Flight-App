package example.alaa.flightdetails.ui.seatselection.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SeatStatus(
    text : String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Row {
        Box(
            modifier = modifier
                .padding(2.dp)
                .clip(CircleShape)
                .size(14.dp)
                .background(color)
        )

        Text(
            text = text ,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 6.dp),
        )
    }
}
