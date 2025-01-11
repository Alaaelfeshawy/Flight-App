package example.alaa.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.onboarding.R

@Composable
fun PagerContent(modifier: Modifier = Modifier,
                 pagerState: PagerState,
                 image : Int,
                 title : String,
                 subTitle:String,
                 onSkipClicked :() -> Unit ={},
                 onNextClicked :() -> Unit ={},
                 isLastPage : Boolean = false,
) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(colorResource(R.color.orange)),
        verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .weight(1f)
                    .aspectRatio(0.7f),
                painter = painterResource(image),
                contentDescription = null
            )
            Column (
                modifier = Modifier
                    .weight(0.5f)
            ){
                ScreenHeader(
                    modifier = modifier.padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    ),
                    text = title,
                    color = Color.White,
                )

                ScreenSubtitle(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = subTitle,
                    color = Color.White,
                )

                DotsSection(modifier, pagerState)

                FooterSection(
                    isLastPage = isLastPage,
                    onNextClicked = onNextClicked,
                    modifier = modifier,
                    onSkipClicked = onSkipClicked
                )
            }
    }

}

@Composable
private fun DotsSection(
    modifier: Modifier,
    pagerState: PagerState
) {
    Row(
        modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(
                vertical = 24.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color.White else colorResource(
                    R.color.grey
                )
            val size = if (pagerState.currentPage == iteration) 16.dp else 12.dp
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(size)
            )
        }
    }
}

@Composable
private fun FooterSection(
    isLastPage: Boolean,
    onNextClicked: () -> Unit,
    modifier: Modifier,
    onSkipClicked: () -> Unit
) {
    if (isLastPage) {
        Button(
            onClick = onNextClicked,
            modifier = modifier
                .fillMaxWidth()
                .height(58.dp)
                .padding(horizontal = 12.dp),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.White

            )
        ) {
            Text(
                text = "Get Started",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = modifier
                    .align(
                        Alignment.CenterVertically
                    )
                    .clickable {
                        onSkipClicked.invoke()
                    },
                text = stringResource(R.string.skip),
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                ),
                textAlign = TextAlign.Start,
            )
            IconButton(onClick = onNextClicked) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = modifier,
                        text = stringResource(R.string.next),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Onboarding1Preview(){
    PagerContent(
        pagerState = rememberPagerState(pageCount = {3}),
        title = "Easy To Use. Easy Flight Search.",
        subTitle = "“User-friendly interface. Efficient flight search.”\n",
        onNextClicked = {},
        onSkipClicked = {},
        modifier = Modifier,
        image = R.drawable.plane1,
        isLastPage = false
    )
}