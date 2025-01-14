package example.alaa.registration.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import example.alaa.base.component.PrimaryMainButton
import example.alaa.base.component.ScreenHeader
import example.alaa.base.component.ScreenSubtitle
import example.alaa.registration.R
import example.alaa.registration.route.NavigationItem

@Composable
fun SuccessScreen(modifier: Modifier = Modifier, navController: NavHostController?=null) {
    Scaffold(
        containerColor = Color.White,
    ) {
        Column(modifier = modifier
            .padding(it)
            .fillMaxSize()
            .background(colorResource(example.alaa.base.R.color.white))) {
            Image(
                modifier = Modifier.aspectRatio(0.9f),
                painter = painterResource(R.drawable.img),
                contentDescription = null
            )
            Image(
                modifier = modifier.align(Alignment.CenterHorizontally)
                    .size(80.dp),
                painter = painterResource(R.drawable.done),
                contentDescription = null,

            )
            ScreenHeader(
                modifier = modifier
                    .fillMaxWidth()
                    .padding( 20.dp),
                text = stringResource(R.string.ready_to_fly),
                textAlignment = TextAlign.Center
            )
            ScreenSubtitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    ),
                text = stringResource(R.string.your_account_has_been_created_successfully),
                textAlign = TextAlign.Center
            )

            Column (
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                PrimaryMainButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        )
                        .height(56.dp),
                    buttonText = stringResource(R.string.done),
                    enabled = true,
                    onButtonClick = {
                        navController?.navigate(NavigationItem.Login.route){
                            popUpTo(NavigationItem.Success.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

    }

}

@Composable
@Preview
fun SuccessScreenPreview(){
    SuccessScreen()
}