package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val TAG = "DISPATCHERS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        ========================= EXECUTION TIMELINE =========================

        IMPORTANT RULE:
        Code order ≠ Execution order in Coroutines

        ---------------------------------------------------------------------
        1) GlobalScope.launch(Dispatchers.Main)
        ---------------------------------------------------------------------
        - Creates a coroutine
        - DOES NOT execute immediately
        - Posts the task to Main thread's event queue
        - Execution will happen LATER when Main thread is free
        */

        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "GlobalScope launch --> Thread = ${Thread.currentThread().name}")
        }

        /*
        ---------------------------------------------------------------------
        2) GlobalScope.async(Dispatchers.Main)
        ---------------------------------------------------------------------
        - Same behavior as launch
        - Creates a coroutine
        - Scheduled on Main thread queue
        - DOES NOT block current thread
        - Execution happens AFTER current work finishes
        */

        GlobalScope.async(Dispatchers.Main) {
            Log.d(TAG, "GlobalScope async --> Thread = ${Thread.currentThread().name}")
        }

        /*
        ---------------------------------------------------------------------
        3) runBlocking(Dispatchers.IO)
        ---------------------------------------------------------------------
        - SPECIAL coroutine builder
        - BLOCKS the current thread immediately
        - Executes its body RIGHT NOW
        - Does NOT get queued
        - That is why this log prints FIRST
        */

        runBlocking(Dispatchers.IO) {
            Log.d(TAG, "runBlocking --> Thread = ${Thread.currentThread().name}")
        }

        /*
        ========================= FINAL EXECUTION ORDER =========================

        1) runBlocking()  --> Executes immediately (blocks)
        2) launch()       --> Executes later on Main thread
        3) async()        --> Executes later on Main thread

        Even though runBlocking is written LAST,
        it executes FIRST because it BLOCKS immediately.

        =======================================================================
        */
    }
}

/*OutPut :-

Reason why runBlocking is executed Frist explained in the comments.

04:57:50.995 DISPATCHERS              D  runBlocking --> Thread = DefaultDispatcher-worker-1
04:57:51.123 DISPATCHERS              D  GlobalScope launch --> Thread = main
04:57:51.123 DISPATCHERS              D  GlobalScope async --> Thread = main

*/