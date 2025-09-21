package com.androidtutorials.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Log.d(TAG, "onCreate: ")
        }
    }
}


@Composable
fun MainScreen() {

    Log.d(TAG, " Inside MainScreen() method ")

    var counter = 0

    var incrementCounter = {
        counter++
        Log.d(TAG, " Counter Incremented $counter")
    }

    var decrementCounter = {
        counter--
        Log.d(TAG, " Counter Decremented $counter")
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                incrementCounter.invoke()
            }) {
                Text(text = "Increment")
            }
            Text(text = "$counter")

            Button(onClick = {
                decrementCounter.invoke()
            }) {
                Text(text = "Decrement")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}