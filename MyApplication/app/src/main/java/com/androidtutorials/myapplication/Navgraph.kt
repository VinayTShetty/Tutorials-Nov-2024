package com.androidtutorials.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable(route = "Home") {
            HomeScreen(navController)
        }
        composable(route = "Details?name={name}&age={age}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                    defaultValue = "No USER"
                },
                navArgument(name = "age") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )) { navBackStackEntry ->
            val name = if (navBackStackEntry.arguments?.getString("name").isNullOrBlank()) "No USER"
            else navBackStackEntry.arguments?.getString("name")!!

            val age = navBackStackEntry.arguments?.getInt("age")
                    DetailsScreen(name,age)
        }
    }
}
