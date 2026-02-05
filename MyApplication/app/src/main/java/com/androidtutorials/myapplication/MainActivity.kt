package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * MainActivity is the entry point of the app.
 * It creates the LiveData object and passes it to Compose UI.
 */
class MainActivity : ComponentActivity() {

    /**
     * CounterLiveData instance.
     * This survives as long as Activity is alive.
     */
    private val counterLiveData = CounterLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * setContent launches Jetpack Compose UI.
         */
        setContent {
            CounterScreen(counterLiveData)
        }
    }
}
