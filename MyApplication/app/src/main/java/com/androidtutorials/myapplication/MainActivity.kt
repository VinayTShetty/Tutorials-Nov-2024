package com.androidtutorials.myapplication

// Android Activity base class for Compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Layout & UI imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text

// Compose state management
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// Alignment & Modifiers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag


// MainActivity is the entry point of the app
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent launches Compose UI inside Activity
        setContent {

            // Calling the Composable function
            // This renders the CounterScreen UI
            CounterScreen()
        }
    }
}


// Top-level Composable function (NOT inside Activity)
// This makes it reusable and testable
@Composable
fun CounterScreen(){

    // remember {} → survives recomposition
    // mutableStateOf(0) → creates observable state
    // Whenever count changes, UI recomposes automatically
    var count by remember { mutableStateOf(0) }

    // Column is a vertical layout container
    Column(
        modifier = Modifier.fillMaxSize(), // takes full screen
        horizontalAlignment = Alignment.CenterHorizontally, // center horizontally
        verticalArrangement = Arrangement.Center // center vertically
    ) {

        // Text composable to display count
        Text(
            text = "Count : $count",

            // testTag helps identify this node in UI tests
            modifier = Modifier.testTag("counter-text-tag")
        )

        // Button composable
        Button(
            // When clicked → increase count
            // This triggers recomposition automatically
            onClick = { count++ },

            // testTag used to find this button in tests
            modifier = Modifier.testTag("increment-button-tag")
        ) {
            // Button label
            Text("Increment")
        }
    }
}