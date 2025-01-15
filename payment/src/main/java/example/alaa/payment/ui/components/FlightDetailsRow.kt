package example.alaa.payment.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun FlightDetailsRow(
    label : String,
    value : String,
    label1 : String,
    value1 : String,
    label2 : String,
    value2 : String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label ,
            value = value,
            labelTextAlign = TextAlign.Start,
            valueTextAlign = TextAlign.Start
        )

        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label1 ,
            value = value1,
            labelTextAlign = TextAlign.Center,
            valueTextAlign = TextAlign.Center,
        )

        ColumnValues(
            modifier = Modifier.weight(1f),
            label = label2 ,
            value = value2,
            labelTextAlign = TextAlign.End,
            valueTextAlign = TextAlign.End,
        )
    }
}

