package com.androidtutorials.androidhelloworld

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyLogic {

    /*
     * CoroutineScope needs a CoroutineContext.
     *
     * CoroutineContext = Dispatcher + Job
     *
     * 1) Dispatcher
     *    - Decides WHICH THREAD the coroutine will run on
     *    - Dispatchers.IO is used for background work
     *      (network calls, database, file I/O)
     *
     * 2) Job
     *    - Controls the lifecycle of the coroutine
     *    - Allows cancellation of all coroutines in this scope
     *
     * Why do we provide both here?
     * --------------------------------
     * This is a CUSTOM CoroutineScope.
     * We are creating the scope from scratch.
     *
     * Kotlin does NOT know:
     * ❓ Where the coroutine should run
     * ❓ Who owns the coroutine lifecycle
     *
     * So WE must explicitly provide:
     * - Dispatcher → where to run
     * - Job        → how long it should live
     */
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    fun startWork() {
        scope.launch {
            Log.d(AppContansts.TAG, "Start Custom Coroutine")
            delay(7000)
            Log.d(AppContansts.TAG, "End Custom Coroutine")
        }
    }

    fun cancelWork() {
        scope.cancel() // Cancels the Job → cancels all coroutines in this scope
        Log.d(AppContansts.TAG, "Coroutine Work Cancelled")
    }
}
