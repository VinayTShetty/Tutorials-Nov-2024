package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    val TAG = "CANCEL-JOB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1️⃣ Launching a coroutine in GlobalScope
        // This creates a background coroutine immediately.
        val job = GlobalScope.launch {

            // 2️⃣ repeat loop runs 1000 times
            // IMPORTANT: This loop has NO suspend function inside.
            // So it runs very fast without giving chance for cancellation.
            repeat(1000) { i ->
                Log.d(TAG, "Counter Value $i")
                if(i==499){
                    delay(100)
                    Log.d(TAG, "Called Delay Function which makes Coroutine Co-operative")
                }
            }
        }

        // 3️⃣ Cancel is called immediately after launching
        // cancel() only REQUESTS cancellation.
        // It does NOT forcefully stop the coroutine.
        job.cancel()

        // 4️⃣ Checking job status
        // This may print false (cancelled),
        // but coroutine might have already finished execution.
        Log.d(TAG, "Job Status " + job.isActive)

        Log.d(TAG, "Reached End of Method")
    }
}

