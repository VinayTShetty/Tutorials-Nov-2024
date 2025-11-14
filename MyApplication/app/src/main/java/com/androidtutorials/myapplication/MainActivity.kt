package com.androidtutorials.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

val TAG = "SIDE_EFFECT_DEMO"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SideEffectDemoUI()
                }
            }
        }
    }
}

@Composable
fun SideEffectDemoUI() {

    var counter by remember { mutableStateOf(0) }

    // Runs after EVERY recomposition
    SideEffect {
        Log.d(TAG, "SideEffect Executed → UI recomposed. Counter = $counter")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "SideEffect Demo",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Counter: $counter",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            counter++
            Log.d(TAG, "👉 Button Clicked → Counter = $counter")
        }) {
            Text(text = "Increase ($counter)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSideEffectDemo() {
    MyApplicationTheme {
        SideEffectDemoUI()
    }
}
