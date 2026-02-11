package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val TAG = "COROUTINE-BUILDER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ----------------------------------------------------
        // Coroutine Builder: launch
        // Purpose: Fire-and-forget (no result)
        // Scope: GlobalScope (NOT recommended for Android UI)
        // ----------------------------------------------------
        GlobalScope.launch {
            Log.d(TAG, "GlobalScope + launch")
        }

        // ----------------------------------------------------
        // Coroutine Builder: async
        // Purpose: Returns a result (Deferred<T>)
        // NOTE: await() is required to get result
        // ----------------------------------------------------
        GlobalScope.async {
            Log.d(TAG, "GlobalScope + async")
        }

        // ----------------------------------------------------
        // Coroutine Builder: runBlocking
        // Purpose: Blocks the current thread
        // WARNING: Blocks MAIN thread → avoid in Android UI
        // ----------------------------------------------------
        runBlocking {
            Log.d(TAG, "runBlocking executed")
        }
    }
}
