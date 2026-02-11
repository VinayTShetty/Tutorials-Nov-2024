package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "JOB-OBJECT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ------------------------------------------------------------
        // async coroutine builder
        // - Used when we need a RESULT from coroutine
        // - Returns Deferred<T>
        // - Starts immediately (unless LAZY specified)
        // ------------------------------------------------------------
        val defereedJOb: Deferred<Int> = GlobalScope.async {

            // Actual Business Logic
            // This value will be returned as result of async
            10 * 10
        }

        // ------------------------------------------------------------
        // Checking coroutine state
        // ------------------------------------------------------------
        Log.d(TAG, "Async Is Active = " + defereedJOb.isActive)
        Log.d(TAG, "Async Is Cancelled = " + defereedJOb.isCancelled)

        // ------------------------------------------------------------
        // await() is required to get the RESULT
        // await() is a suspend function
        // So it must be called inside another coroutine
        // ------------------------------------------------------------
        GlobalScope.launch {

            // await() suspends until async finishes
            // Then returns the result (100)
            Log.d(TAG, "The Output Result is " + defereedJOb.await())
        }
    }
}
