package com.androidtutorials.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var nameValue by remember {
            mutableStateOf("")
        }
        var ageValue by remember {
            mutableStateOf("")
        }
        Text(text = "HomeScreen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(65.dp))
        TextField(
            value = nameValue,
            onValueChange = { nameValue = it },
            modifier = Modifier.padding(10.dp),
            placeholder = {
                Text(
                    text = ("Enter Your Name")
                )
            })
        TextField(
            value = ageValue,
            onValueChange = { ageValue = it },
            modifier = Modifier.padding(10.dp),
            placeholder = {
                Text(
                    text = ("Enter Your Age")
                )
            })
        Button(onClick = {
            navController.navigate("Details?name=$nameValue&age=$ageValue")
        }) {
            Text(text = "Pass Data", fontSize = 30.sp)
        }
    }
}