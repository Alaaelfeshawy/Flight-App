package example.alaa.home.ui.component.tabs.multicity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddOrRemovingFields(
    onPrimaryButtonClick : ()->Unit,
    onSecondButtonClick : ()->Unit,
    listSize : Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onPrimaryButtonClick
        ) {
            Text(
                "Add" ,
                color = colorResource(example.alaa.base.R.color.orange),
                fontSize = 16.sp
                )
        }


        if (listSize >= 2) {
            TextButton(
                onClick = onSecondButtonClick
            ) {
                Text(
                    "Remove" ,
                    color = colorResource(example.alaa.base.R.color.grey),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun AddOrRemovingFieldsPreview(){
    AddOrRemovingFields(
        onPrimaryButtonClick = {},
        onSecondButtonClick = {},
        2
    )
}
