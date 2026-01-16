package com.androidtutorials.androidhelloworld

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ViewModel survives configuration changes
class MyViewModel : ViewModel() {

    // Business logic should live in ViewModel
    fun startMyTask() {
        viewModelScope.launch {

            Log.d(AppConstants.TAG, "startMyTask: START ViewModel-Scope")

            delay(7000) // Simulating long-running work

            Log.d(AppConstants.TAG, "startMyTask: END ViewModel-Scope")
        }
    }

    // Called when ViewModel is destroyed
    override fun onCleared() {
        super.onCleared()
        Log.d(AppConstants.TAG, "ViewModel Cleared")
    }
}
