package com.androidtutorials.androidhelloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // ViewModel tied to THIS activity
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(AppConstants.TAG, "onCreate: MainActivity Entered")

        // Start coroutine inside ViewModel
        viewModel.startMyTask()

        // Navigate to SecondActivity
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            Log.d(AppConstants.TAG, "Navigating to Second Activity")
            startActivity(Intent(this, SeconActivity::class.java))
            finish() // destroys MainActivity → ViewModel cleared
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(AppConstants.TAG, "onDestroy: MainActivity")
    }
}
