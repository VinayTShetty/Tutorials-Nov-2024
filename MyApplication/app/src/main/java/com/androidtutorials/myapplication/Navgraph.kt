package com.androidtutorials.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun navGraph() {
    //create a navController for navigation
    val navController = rememberNavController()

    //use navigation host
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") { homeScreen(navController = navController) }
        // /{name}/{age} It indicates the dynamic place holders
        composable(
            "my_resultScreen/{name}/{age}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("age") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name")
            val age = navBackStackEntry.arguments?.getString("age")
            resultScreen(name = name, age = age)
        }
    }
}