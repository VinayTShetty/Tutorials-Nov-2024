package com.androidtutorials.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.androidtutorials.navigation.Screens


@Composable
fun forgetPassScreen(navController: NavController) {
    
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        
        Text(text = "Forgot Pass" , fontSize = 40.sp)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {navController.navigate(Screens.ScreenLogin.route) }) {
            Text(text = "Go to Login", fontSize = 25.sp)
        }
    }

}