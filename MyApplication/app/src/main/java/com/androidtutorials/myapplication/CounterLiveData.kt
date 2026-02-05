package com.androidtutorials.myapplication

import androidx.lifecycle.MutableLiveData

/**
 * This class is responsible for holding and managing counter data.
 * It uses LiveData so that UI can observe changes automatically.
 */
class CounterLiveData {

    /**
     * MutableLiveData holds the counter value.
     * Initial value is set to 0.
     *
     * MutableLiveData is observable and lifecycle-aware.
     */
    val counter = MutableLiveData<Int>(0)

    /**
     * Increments the counter value by 1.
     * Safe call is used in case value is null.
     */
    fun incrementCounter() {
        counter.value = (counter.value ?: 0) + 1
    }

    /**
     * Decrements the counter value by 1.
     */
    fun decrementCounter() {
        counter.value = (counter.value ?: 0) - 1
    }
}
