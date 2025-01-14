package example.alaa.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropDownComponent(
    modifier: Modifier = Modifier,
    onDropDownClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemSelected: (Int) -> Unit,
    list : List<String>,
    selectedItemPosition : Int,
    isDropDownExpanded : Boolean,
    title : String
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 14.dp
            )
    ) {
        CustomField(
            inputTextModifier = Modifier.clickable { onDropDownClicked.invoke() },
            value = list[selectedItemPosition],
            title = title,
            isReadOnly = true,
            suffix = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            enabled = false
        )
        DropDownList(
            isDropDownExpanded = isDropDownExpanded,
            list = list,
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            onItemSelected = { itemPosition ->
                onItemSelected.invoke(itemPosition)
            }
        )
    }
}