package example.alaa.home.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.R
import example.alaa.home.ui.HomeState
import example.alaa.home.ui.model.OffersUiModel

@Composable
fun OffersSection(modifier: Modifier = Modifier , state: HomeState) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(
            text = stringResource(example.alaa.home.R.string.current_offer),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = stringResource(example.alaa.home.R.string.see_all),
            color = colorResource(R.color.orange),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }

    Column {
        LazyRow {
            items(state.offers) {
                OfferCard(it)
            }
        }
    }
}

@Composable
fun OfferCard(it: OffersUiModel) {
    Card(
        modifier = Modifier.padding(12.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row (
            modifier = Modifier
                .heightIn(max = 120.dp)
                .width(264.dp)
        ){
            Box(
                modifier = Modifier
                    .background(colorResource(it.backgroundColor)),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        modifier = Modifier.size(42.dp),
                        painter = painterResource(it.logo),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(example.alaa.home.R.string.off, it.offer.toInt()),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,){
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                    text = stringResource(
                        example.alaa.home.R.string.discount_with_mastercard,
                        it.offer.toInt()
                    ),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                )

                Text(
                    modifier =  Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 6.dp,
                        bottom = 16.dp
                    ),
                    text = stringResource(example.alaa.home.R.string.get_a_discount_when_you_use_this),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(R.color.grey)
                    )
                )
            }
        }
    }
}