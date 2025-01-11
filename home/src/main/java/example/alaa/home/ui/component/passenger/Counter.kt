package example.alaa.home.ui.component.passenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R
import example.alaa.home.ui.model.Action

@Composable
fun Count(modifier: Modifier = Modifier, count : Int, onClick : (Action, Int) -> Unit) {

    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface (
            modifier = Modifier
                .size(24.dp),
            shape = CircleShape,
            color = colorResource(R.color.orange),
        ) {
            IconButton(
                onClick = {
                    onClick.invoke(Action.INCREASE , count)
                }
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Text("$count" , fontSize = 24.sp , modifier = Modifier.padding(4.dp))

        Surface (
            modifier = Modifier
                .size(24.dp),
            shape = CircleShape,
            color = colorResource(R.color.orange),
        ){
            IconButton(
                onClick = {
                    onClick.invoke(Action.DECREASE , count)
                },

                ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}
