package example.alaa.base.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Styles {

    object TripLineStyles {
        val titleStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
//        val subtitleStyle = TextStyle(
//            fontSize = 10.sp,
//            fontWeight = FontWeight.Light,
//            color = Color.White
//        )
        val subtitleStyle = TextStyle(
            fontSize = 10.sp,
            color = Color.White
        )
    }


    object TripTimeStyles {
        val departTimeLabelStyle = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        val departTimeValueStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
//        val arrivalTimeLabelStyle = TextStyle(
//            fontSize = 10.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color.Gray,
//            textAlign = TextAlign.End
//        )

//        val arrivalTimeValueStyle = TextStyle(
//            fontSize = 12.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.Black,
//            textAlign = TextAlign.End
//        )
        val subtitleTextStyle = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
//        val stopsTextStyle = TextStyle(
//            fontSize = 10.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color.Gray
//        )
    }

}