package com.androidtutorials.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidtutorials.myapplication.MainActivity.Companion.TAG
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    Log.d(TAG, "MainScreen: ")
    var count_ by remember { mutableStateOf(0) }

    // ✅ Explicit type annotations

    val onIncrement_: () -> Unit = {
        Log.d(TAG, "🔥 onIncrement lambda executed")
        count_++
    }

    val onDecrement_: () -> Unit = {
        Log.d(TAG, "🔥 onDecrement lambda executed")
        count_--
    }

    CounterRow(count=count_,onIncrement=onIncrement_,onDecrement=onDecrement_)
}

@Composable
fun CounterRow(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Log.d(TAG, "👉 CounterRow() COMPOSABLE executed")

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                Log.d(TAG, "🔘 Increment Button clicked")
                onIncrement()
            }) {
                Text(text = "Increment")
            }

            Text(text = count.toString())

            Button(onClick = {
                Log.d(TAG, "🔘 Decrement Button clicked")
                onDecrement()
            }) {
                Text(text = "Decrement")
            }
        }
    }
}