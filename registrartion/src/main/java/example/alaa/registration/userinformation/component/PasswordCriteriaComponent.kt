package example.alaa.registration.userinformation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import example.alaa.base.component.ScreenSubtitle
import example.alaa.registration.userinformation.UserInformationState

@Composable
fun PasswordCriteriaComponent(state: UserInformationState) {
    state.passwordCriteria.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = if (it.isMet) Icons.Default.Check else Icons.Default.Close,
                tint = if (it.isMet) Color.Green else Color.Red,
                contentDescription = null
            )

            ScreenSubtitle(
                modifier = Modifier.padding(4.dp),
                text = stringResource(it.nameRes)
            )
        }
    }
}
