package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MainActivity
 *
 * This example demonstrates the behavior of GlobalScope in an Android Activity.
 * It shows how a coroutine launched in GlobalScope is NOT tied to the Activity lifecycle.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        // TAG used for filtering logs in Logcat
        private const val TAG = "GlobalScopeDemo"
    }

    /**
     * onCreate()
     *
     * Called when the Activity is created.
     * The UI is initialized and a GlobalScope coroutine is started.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Attaches activity_main.xml to this Activity
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Activity Created")

        /**
         * GlobalScope.launch
         *
         * - Launches a coroutine that is NOT lifecycle-aware
         * - This coroutine is NOT cancelled when:
         *     • Activity is destroyed
         *     • Screen is rotated
         *     • User navigates away
         *
         * - Coroutine lives until:
         *     • App process is killed
         *     • It is manually cancelled
         */
        GlobalScope.launch {

            Log.d(TAG, "Coroutine Started")

            /**
             * delay(5000)
             *
             * - Suspends the coroutine for 5 seconds
             * - Does NOT block the main thread
             * - Activity may already be destroyed during this delay
             */
            delay(5000)

            /**
             * This log will still print EVEN IF:
             * - onDestroy() has already been called
             * - Activity no longer exists
             *
             * This demonstrates why GlobalScope is dangerous in UI code.
             */
            Log.d(TAG, "Coroutine Finished after the Delay")
        }
    }

    /**
     * onDestroy()
     *
     * Called when the Activity is destroyed.
     * IMPORTANT:
     * - This does NOT cancel the GlobalScope coroutine
     * - Coroutine continues running in the background
     */
    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: Activity Destroyed")
    }
}
