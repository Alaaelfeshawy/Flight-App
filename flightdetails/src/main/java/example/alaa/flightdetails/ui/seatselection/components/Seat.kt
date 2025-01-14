package example.alaa.flightdetails.ui.seatselection.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import example.alaa.base.R
import example.alaa.base.component.CustomField
import example.alaa.flightdetails.ui.seatselection.model.SeatModel
import example.alaa.flightdetails.ui.seatselection.model.SeatStatus

@Composable
fun Seat(seat : SeatModel? , onClick: (SeatModel?) -> Unit) {

   val color =  when(seat?.status){
        SeatStatus.AVAILABLE -> TextFieldDefaults.colors(
            disabledContainerColor = Color.White,
            disabledIndicatorColor = colorResource(R.color.orange) ,
        )
        SeatStatus.SELECTED -> TextFieldDefaults.colors(
            disabledContainerColor = colorResource(R.color.orange),
            disabledIndicatorColor = colorResource(R.color.orange) ,
        )
        SeatStatus.UNAVAILABLE -> TextFieldDefaults.colors(
            disabledContainerColor = colorResource(R.color.gray_02),
            disabledIndicatorColor = colorResource(R.color.gray_02) ,
        )

       null -> TextFieldDefaults.colors(
           disabledContainerColor = Color.White,
           disabledIndicatorColor = colorResource(R.color.orange) ,
       )
   }
    Column(modifier = Modifier.padding(8.dp)) {
        CustomField(
            modifier = Modifier.size(36.dp).clickable(
                enabled = seat?.status != SeatStatus.UNAVAILABLE,
                onClick = {
                    onClick.invoke(seat)
                }
            ),
            isReadOnly = true,
            enabled = false,
            colors = color,
        )
    }
}