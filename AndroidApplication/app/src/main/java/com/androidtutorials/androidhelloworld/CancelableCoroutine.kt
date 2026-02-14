package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity_2 : AppCompatActivity() {

    private val TAG = "CANCEL-JOB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Example:
         * Coroutine gets cancelled when it hits delay()
         *
         * Explanation:
         * - cancel() only sends a cancellation request.
         * - Coroutine continues executing until it reaches
         *   a suspension point.
         * - delay() is a suspend function.
         * - delay() checks for cancellation internally.
         * - If job is already cancelled, it throws
         *   CancellationException.
         * - Coroutine stops immediately at that point.
         */

        val job = GlobalScope.launch {

            repeat(1000) { i ->

                Log.d(TAG, "Counter Value $i")

                if (i == 499) {

                    Log.d(TAG, "About to call delay()")

                    // Suspension point
                    // delay() checks for cancellation
                    delay(100)

                    // This line will NOT execute
                    // if job was cancelled before delay()
                    Log.d(TAG, "This will not print if cancelled")
                }
            }
        }

        // Sending cancellation request immediately
        job.cancel()

        Log.d(TAG, "Job Active: ${job.isActive}")
        Log.d(TAG, "Reached End of Method")
    }
}
