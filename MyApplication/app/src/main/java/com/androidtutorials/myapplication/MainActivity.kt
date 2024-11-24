package com.androidtutorials.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counterViewModel=ViewModelProvider(this)[CounterViewModel::class.java]
            CounterScreen().increment_Decrement(counterViewModel = counterViewModel)
        }
    }
}

