package example.alaa.base.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DropDownList(
    isDropDownExpanded: Boolean,
    list : List<String>,
    onDismissRequest : ()->Unit,
    onItemSelected : (itemPosition : Int) -> Unit
) {
    DropdownMenu(
        expanded = isDropDownExpanded,
        onDismissRequest = onDismissRequest,
        containerColor = Color.White
    ) {
        list.forEachIndexed { index, text ->
            DropdownMenuItem(text = {
                Text(text = text)
            },
                onClick = {
                    onDismissRequest.invoke()
                    onItemSelected.invoke(index)
                }
            )
        }
    }
}
