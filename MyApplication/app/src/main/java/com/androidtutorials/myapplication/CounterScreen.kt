package com.androidtutorials.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class CounterScreen {
    private val TAG = "STATE_MANAGEMENT"


    @Composable
    fun increment_Decrement(counterViewModel: CounterViewModel) {

        Log.i(TAG, "mainScreen_Composoable executed")

        val counter =counterViewModel.counter.observeAsState(initial = 0)

        var incerementCounter = {
            counterViewModel.increaseCounterValue()
            Log.i(TAG, "IncrementLogic: ${counter.value.toString()}")
        }

        var decrementCounter = {
            counterViewModel.decreaseCounterValue()
            Log.i(TAG, "DecrementLogic: ${counter.value.toString()}")
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    incerementCounter.invoke()
                    Log.i(TAG, "Increment Counter clicked ")
                }) {
                    Text(text = "Incerement")
                }
                Text("${counter.value.toString()}")
                Button(onClick = {
                    decrementCounter.invoke()
                    Log.i(TAG, "DecrementCounter")
                }) {
                    Text(text = "Decrement")
                }
            }
        }
    }
}