package example.alaa.base.component.model

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import example.alaa.base.component.Styles

data class TripLocation(
    val title: String,
    val subtitle: String,
    val titleStyle: TextStyle = Styles.TripLineStyles.titleStyle,
    val subtitleStyle: TextStyle = Styles.TripLineStyles.titleStyle.copy(
        fontSize = 10.sp
    ),
)