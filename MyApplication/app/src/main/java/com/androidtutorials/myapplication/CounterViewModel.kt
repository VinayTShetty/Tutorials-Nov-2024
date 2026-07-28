package com.androidtutorials.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val TAG = CounterViewModel::class.java.simpleName

    /**
     * Private MutableLiveData variable.
     * It is not directly exposed to other classes.
     * Initial value is set to 0.
     */
    private val _countervalue = MutableLiveData<Int>().apply {
        value = 0
    }

    /**
     * LiveData exposed to other classes for observing/getting the value.
     * Other classes cannot directly modify the value.
     */
    val counter: LiveData<Int> = _countervalue

    /**
     * Increases the counter value by 1.
     */
    fun increaseCounterValue() {
        Log.i(TAG, "increaseCounterValue: ")

        _countervalue.value = (_countervalue.value ?: 0) + 1
    }

    /**
     * Decreases the counter value by 1.
     */
    fun decreaseCounterValue() {
        Log.i(TAG, "decreaseCounterValue: ")

        _countervalue.value = (_countervalue.value ?: 0) - 1
    }
}