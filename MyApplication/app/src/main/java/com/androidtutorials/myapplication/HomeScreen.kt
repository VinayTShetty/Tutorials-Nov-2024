package com.androidtutorials.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(navController: NavController) {
    var nameInput by remember { mutableStateOf(TextFieldValue("")) }
    var ageInput by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(value = nameInput, onValueChange = {
            nameInput = it
        }, label = { Text(text = "Enter Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(value = ageInput, onValueChange = {
            ageInput = it
        }, label = { Text(text = "Enter Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val route = "my_resultScreen/${nameInput.text}/${ageInput.text}"
                navController.navigate(route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}