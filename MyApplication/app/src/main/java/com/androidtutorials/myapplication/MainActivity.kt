package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

/**
 * Activity is only responsible for UI.
 * ViewModel handles business logic.
 */
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Getting ViewModel instance
        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        setContent {
            CounterScreen(viewModel)
        }
    }

    @Composable
    fun CounterScreen(viewModel: CounterViewModel) {

        /**
         * observeAsState converts LiveData into Compose State.
         *
         * Why needed?
         * Compose only reacts to State.
         * LiveData is not Compose State.
         *
         * So this bridges LiveData → Compose.
         *
         * When LiveData changes,
         * Compose recomposes automatically.
         */
        val counter by viewModel.counter.observeAsState(0)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Button(onClick = { viewModel.incrementCounter() }) {
                Text("Increase")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Counter : $counter",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}