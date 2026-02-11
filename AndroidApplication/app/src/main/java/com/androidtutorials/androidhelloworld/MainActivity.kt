package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    val TAG = "RUN-BLOCKING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ------------------------------------------------------------
        // runBlocking is a coroutine builder
        // It BLOCKS the current thread until coroutine finishes
        // It RETURNS the last expression inside the block
        // ------------------------------------------------------------

        val result = runBlocking {

            // This is the last expression
            // So runBlocking will return 50
            10 * 5
        }

        Log.d(TAG, "Result = $result")
    }
}
