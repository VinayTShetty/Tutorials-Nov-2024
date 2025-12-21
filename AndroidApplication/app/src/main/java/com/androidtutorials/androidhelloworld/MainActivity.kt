package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    // Individual Activity Tag
    private val ACTIVITY_TAG = "Activity-Life-Cycle"

    // Common Tag (shared with Receiver)
    private val COMMON_TAG = "Common-Life-Cycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Activity lifecycle logs
        Log.d(COMMON_TAG, "Application Started")
        Log.d(ACTIVITY_TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(ACTIVITY_TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ACTIVITY_TAG, "onStop")
    }
}

/**
 * Code is not working , as the Battery low/ OKAY is not working.
 * Need to check on the physical device.
 */
