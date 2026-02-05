package com.androidtutorials.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays counter UI.
 * It observes LiveData and updates UI automatically.
 */
@Composable
fun CounterScreen(counterLiveData: CounterLiveData) {

    /**
     * observeAsState converts LiveData into Compose State.
     * Whenever LiveData value changes, recomposition happens.
     */
    val count by counterLiveData.counter.observeAsState(0)

    /**
     * Column used to arrange UI elements vertically.
     */
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        /**
         * Displays current counter value.
         */
        Text(
            text = "Count $count",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        /**
         * Row for increment and decrement buttons.
         * spacedBy provides equal spacing between buttons.
         */
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Button(onClick = { counterLiveData.decrementCounter() }) {
                Text("-")
            }

            Button(onClick = { counterLiveData.incrementCounter() }) {
                Text("+")
            }
        }
    }
}
