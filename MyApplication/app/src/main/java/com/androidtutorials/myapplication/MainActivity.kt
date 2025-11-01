package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ✅ Set the Composable content of the activity
            MyApplicationTheme {
                derivedStateDemo()
            }
        }
    }
}

@Composable
fun derivedStateDemo() {
    // 🧠 Define state variables
    var firstname by remember { mutableStateOf("Vinay") }
    var lastname by remember { mutableStateOf("TS") }

    // ⚙️ derivedStateOf recomputes only when firstname or lastname changes
    val fullname by derivedStateOf {
        "$firstname $lastname"
    }

    // 🧱 Column: places items vertically
    Column(
        modifier = Modifier
            .fillMaxSize(),                         // Make it take full screen
        verticalArrangement = Arrangement.Center,  // Center children vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        // 🏷️ Text Composable — displays full name
        Text(
            text = "Full Name: $fullname",          // The text to show
            fontSize = 24.sp,                       // Text size
            fontWeight = FontWeight.Bold,           // Make text bold
            color = Color.Black                     // Text color
        )

        // 🧩 Button Composable — triggers name change
        Button(
            onClick = { firstname = "John" }        // What happens on click
        ) {
            Text(
                text = "Change First Name"          // Button label
            )
        }
    }
}
