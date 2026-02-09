package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProducedStateDemo()
        }
    }

    /*
     * This is a SUSPEND function.
     * It simulates:
     *  - Network call
     *  - Heavy background operation
     *
     * IMPORTANT:
     * ❌ You CANNOT call this directly inside a @Composable
     * because composables can recompose multiple times.
     */
    suspend fun fetchUserFromAPI(): String {
        delay(1000) // simulate network delay
        return "Vinay From (API)"
    }

    @Composable
    fun ProducedStateDemo() {

        /*
         * PRODUCED STATE
         *
         * - This state is NOT updated by UI
         * - It is PRODUCED by fetchUserFromAPI()
         * - Runs in a coroutine internally
         * - Automatically cancelled when composable leaves screen
         */
        val userName by produceState<String?>(
            initialValue = null
        ) {
            value = fetchUserFromAPI()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /*
             * UI only READS the state
             * UI never sets or mutates the state
             */
            if (userName == null) {
                Text(
                    text = "Loading User ...",
                    fontSize = 22.sp
                )
            } else {
                Text(
                    text = "User : $userName",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
