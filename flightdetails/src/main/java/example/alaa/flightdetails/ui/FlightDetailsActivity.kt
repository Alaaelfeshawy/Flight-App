package example.alaa.flightdetails.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import example.alaa.flightdetails.ui.route.AppNavHost

@AndroidEntryPoint
class FlightDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            AppNavHost(
                modifier = Modifier,
                navController = rememberNavController()
            )
        }
    }
}