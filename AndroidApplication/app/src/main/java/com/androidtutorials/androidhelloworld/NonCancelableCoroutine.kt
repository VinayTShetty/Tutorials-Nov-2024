/**
 * ===============================================================
 * Example 1: Coroutine is NOT Getting Cancelled
 * ===============================================================
 *
 * Explanation:
 * Cancellation in Kotlin Coroutines is COOPERATIVE.
 *
 * In this example:
 * - The coroutine does not call any suspend function.
 * - It does not check for isActive.
 * - The repeat loop runs very fast.
 *
 * When job.cancel() is called:
 * - It only sends a cancellation request.
 * - It does NOT forcefully stop the coroutine.
 *
 * Since the coroutine never hits a suspension point
 * (like delay(), yield(), etc.),
 * it does not get a chance to respond to cancellation.
 *
 * As a result, the loop finishes execution normally.
 * ===============================================================
 */

package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity_1 : AppCompatActivity() {

 private val TAG = "CANCEL-JOB"

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_main)

  // 1️⃣ Launching a coroutine
  val job = GlobalScope.launch {

   // 2️⃣ No suspend function inside loop
   // This loop runs very fast
   repeat(1000) { i ->
    Log.d(TAG, "Counter Value $i")
   }
  }

  // 3️⃣ Sending cancellation request
  job.cancel()

  // 4️⃣ Checking job state
  Log.d(TAG, "Job Active: ${job.isActive}")

  Log.d(TAG, "Reached End of Method")
 }
}
