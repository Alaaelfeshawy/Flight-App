package example.alaa.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.alaa.base.component.CustomField
import example.alaa.base.component.DropDownList

@Composable
fun ClassComponent(
    modifier: Modifier = Modifier,
    onDropDownClicked: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemSelected: (Int) -> Unit,
    planeClasses : List<String>,
    selectedClassPosition : Int,
    isClassDropDownExpanded : Boolean
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = 14.dp
            )
    ) {
        CustomField(
            inputTextModifier = Modifier.clickable { onDropDownClicked.invoke() },
            value = planeClasses[selectedClassPosition],
            title = "Class",
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
            isDropDownExpanded = isClassDropDownExpanded,
            list = planeClasses,
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            onItemSelected = { itemPosition ->
                onItemSelected.invoke(itemPosition)
            }
        )
    }
}