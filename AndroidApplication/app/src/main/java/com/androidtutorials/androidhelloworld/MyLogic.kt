package com.androidtutorials.androidhelloworld

import android.util.Log
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel

class MyLogic {

    // MainScope already includes:
    // Dispatchers.Main + SupervisorJob
    private var scope = MainScope()

    fun startWork() {
        scope.launch {
            Log.d(AppContansts.TAG, "MainScope Coroutine STARTED")
            delay(5000)
            Log.d(AppContansts.TAG, "MainScope Coroutine FINISHED")
        }
    }

    fun cancelWork() {
        scope.cancel()
        Log.d(AppContansts.TAG, "MainScope CANCELLED")

        // Cancelled scope is DEAD → recreate to reuse
        scope = MainScope()
    }
}
