package com.androidtutorials.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel holds UI state.
 * It survives configuration changes (like screen rotation).
 * It should NOT contain UI code.
 */
class CounterViewModel : ViewModel() {

    /**
     * Backing property (MutableLiveData)
     * Only ViewModel can modify this.
     */
    private val _counter = MutableLiveData(0)

    /**
     * Exposed as immutable LiveData.
     * UI can observe but cannot modify.
     */
    val counter: LiveData<Int> = _counter

    /**
     * Business logic.
     * Increases counter value.
     */
    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }
}