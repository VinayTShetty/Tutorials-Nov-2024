package com.androidtutorials.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.androidtutorials.myapplication.screens.forgetPassScreen
import com.androidtutorials.myapplication.screens.homeScreen
import com.androidtutorials.myapplication.screens.loginScreen
import com.androidtutorials.myapplication.screens.registerScreen
import com.androidtutorials.myapplication.screens.screenA
import com.androidtutorials.myapplication.screens.screenB


@Composable
fun nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.authRoute.route) {

        navigation(startDestination=Screens.ScreenLogin.route,route=Screens.authRoute.route) {

            composable(route = Screens.ScreenLogin.route){
                loginScreen(navController = navController)
            }

            composable(route = Screens.ScreenRegister.route){
                registerScreen(navController = navController)
            }

            composable(route = Screens.ScreenForgotPass.route){
                forgetPassScreen(navController = navController)
            }
        }


        navigation(startDestination=Screens.ScreenHome.route,route=Screens.appRoute.route) {

            composable(route = Screens.ScreenHome.route){
                homeScreen(navController = navController)
            }

            composable(route = Screens.ScreenA.route){
                screenA(navController = navController)
            }

            composable(route = Screens.ScreenB.route){
                screenB(navController = navController)
            }
        }
    }
}