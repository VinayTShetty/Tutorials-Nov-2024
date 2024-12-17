package com.androidtutorials.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun resultScreen(name: String?, age: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "$name", style = MaterialTheme.typography.bodyLarge)
        Text(text = "$age", style = MaterialTheme.typography.bodyLarge)
    }
}