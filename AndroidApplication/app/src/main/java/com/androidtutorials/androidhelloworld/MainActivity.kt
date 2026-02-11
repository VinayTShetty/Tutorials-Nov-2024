package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "COROUTINE-BUILDER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ----------------------------------------------------
        // launch coroutine builder
        // - Starts a coroutine immediately
        // - Does NOT return a result
        // - Returns a Job object to control coroutine lifecycle
        // ----------------------------------------------------
        val job: Job = GlobalScope.launch {
            Log.d(TAG, "Global Scope Launch -Job Object")
        }

        // ----------------------------------------------------
        // Job state checks
        // ----------------------------------------------------

        // isActive → true if coroutine is running or scheduled
        Log.d(TAG, "Job is Active " + job.isActive)

        // isCancelled → true if coroutine was cancelled
        Log.d(TAG, "Job is Cancelled " + job.isCancelled)

        // start() → ONLY useful for LAZY coroutines
        // Here, coroutine is already started automatically,
        // so start() returns false
        Log.d(TAG, "Job is Started " + job.start())
    }
}
