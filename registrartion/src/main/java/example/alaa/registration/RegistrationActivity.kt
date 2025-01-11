package example.alaa.registration

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import example.alaa.registration.route.AppNavHost

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost(navController = rememberNavController())
        }
    }
}