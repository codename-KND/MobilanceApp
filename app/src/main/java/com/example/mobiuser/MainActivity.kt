package com.example.mobiuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobiuser.presentation.GetTripsScreen.MyApp
import com.example.mobiuser.ui.theme.driversUI.driverHome
import com.example.mobiuser.ui.theme.driversUI.driverLogin
import com.example.mobiuser.presentation.shared.Launch
import com.example.mobiuser.ui.theme.MobiUserTheme
import com.example.mobiuser.ui.theme.userUI.Home
import com.example.mobiuser.presentation.loginScreen.Login
import com.example.mobiuser.presentation.requestAmbulance.Request
import com.example.mobiuser.presentation.signUpScreen.SignUp
import com.example.mobiuser.presentation.shared.prelaunch
import com.example.mobiuser.ui.theme.userUI.mapScreen
import dagger.hilt.android.AndroidEntryPoint

sealed class  Goto(val route: String){
    object Launch: Goto("Launch")
    object Login: Goto("Login")
    object Home: Goto("Home")
    object SignUp: Goto("SignUp")
    object Request: Goto("Request")
    object Prelaunch: Goto("Prelaunch")
    object DriverLogin: Goto("driverLogin")
    object DriverHome: Goto("driverHome")
    object MapView: Goto("MapView")
    object MyApp: Goto("MyApp")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController =rememberNavController()
                    NavigationAppHost(navController = navController)
                }
            }
        }
    }

}

@Composable
fun NavigationAppHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Login" ){

        composable(Goto.Prelaunch.route){ prelaunch(navController) }
        composable(Goto.Launch.route){ Launch(navController) }
        composable(Goto.Login.route){Login(navController) }
        composable(Goto.Home.route){ Home(navController) }
        composable(Goto.SignUp.route){ SignUp(navController) }
        composable(Goto.Request.route){ Request(navController) }
        composable(Goto.DriverHome.route){ driverHome(navController) }
        composable(Goto.DriverLogin.route){ driverLogin(navController) }
        composable(Goto.MapView.route){ mapScreen(navController) }
        composable(Goto.MyApp.route){ MyApp() }

        }

}