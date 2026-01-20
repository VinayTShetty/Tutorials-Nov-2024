package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val TAG = "DISPATCHERS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ===================== CASE 1 : Dispatchers.IO =====================

        runBlocking(Dispatchers.IO) {

            Log.d(
                TAG,
                "runBlocking(IO): Parent coroutine running on IO dispatcher " +
                        "| Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
            )

            launch {
                Log.d(
                    TAG,
                    "launch(IO): Child coroutine inherits IO dispatcher " +
                            "| Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )
            }
        }

        // ===================== CASE 2 : Dispatchers.Main (default for runBlocking in Android) =====================

        runBlocking {

            Log.d(
                TAG,
                "runBlocking(Main): Parent coroutine running on Main dispatcher " +
                        "| Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
            )

            launch {
                Log.d(
                    TAG,
                    "launch(Main): Child coroutine inherits Main dispatcher " +
                            "| Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )
            }
        }

        /*
        ===================== IMPORTANT NOTES : DISPATCHER INHERITANCE =====================

        1) Dispatcher inheritance means:
           - Child coroutine uses the SAME DISPATCHER as parent
           - NOT necessarily the same THREAD

        --------------------------------------------------------------------

        2) Dispatchers.IO
           - It is a THREAD POOL (multiple threads)
           - Parent may run on: DefaultDispatcher-worker-1
           - Child may run on:  DefaultDispatcher-worker-3
           - SAME dispatcher ✔
           - DIFFERENT threads ✔ (expected behavior)

           So this is CORRECT:
           "Not the same thread in Dispatchers.IO because it is a pool of threads"

        --------------------------------------------------------------------

        3) Dispatchers.Main
           - It has ONLY ONE thread (UI thread)
           - Parent runs on: main
           - Child runs on:  main
           - SAME dispatcher ✔
           - SAME thread ✔

           So this is CORRECT:
           "In Dispatchers.Main, parent and child run on the same thread"

        --------------------------------------------------------------------

        4) Golden Rule:
           Dispatcher inheritance = same thread pool
           Thread inheritance     = NOT guaranteed

        --------------------------------------------------------------------

        5) runBlocking is used here ONLY for learning.
           ❌ Do NOT use runBlocking in onCreate() in real apps.
           ✅ Use lifecycleScope.launch instead.

        ====================================================================
        */
    }
}
