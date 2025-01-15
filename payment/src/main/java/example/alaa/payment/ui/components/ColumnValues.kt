package example.alaa.payment.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R


@Composable
fun ColumnValues(
    modifier: Modifier = Modifier,
    label : String,
    value : String,
    labelTextAlign : TextAlign,
    valueTextAlign : TextAlign
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            textAlign = labelTextAlign,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = value,
            textAlign = valueTextAlign,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = colorResource(R.color.black_01),
        )
    }
}