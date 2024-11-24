package com.androidtutorials.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val TAG = CounterViewModel::class.java.simpleName
    /**
     * Private MutableLiveData variable will not be exposed to other classes.
     * Also add a default value for it.
     */
    private val _countervalue=MutableLiveData<Int>().apply { value=0 }
    /**
     * LiveData exposed to other classes for getting the values.
     */
    val counter:LiveData<Int> = _countervalue

    fun increaseCounterValue(){
        Log.i(TAG, "increaseCounterValue: ")
        _countervalue.value = (counter.value ?: 0) + 1
    }

    fun decreaseCounterValue(){
        Log.i(TAG, "decreaseCounterValue: ")
        _countervalue.value = (counter.value ?: 0) - 1
    }
}