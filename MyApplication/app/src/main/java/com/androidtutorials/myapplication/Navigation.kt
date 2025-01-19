package com.androidtutorials.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "A"){

        composable(route = "A"){
            screenA(navController)
        }

        composable(route = "B"){
            screenB(navController)
        }

        composable(route = "C"){
            screenC(navController)
        }
    }
}