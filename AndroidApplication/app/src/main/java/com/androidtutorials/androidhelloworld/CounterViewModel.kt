package com.androidtutorials.androidhelloworld

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel:ViewModel() {
    /**
     * This value can be easily changed.i.e mutableLiveData , for that reason i have kept it as private.
     * For MutableLive data we cannot set the value directly using constructor i.e  MutableLiveData<Int>(0) .
     * It should be initialized with apply or using init block.
     */
    private val _counter=MutableLiveData<Int>().apply { value=0 }

    val counter: LiveData<Int> = _counter

    fun incrementCountervalue(){
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun decrementCountervalue(){
        _counter.value = (_counter.value ?: 0) - 1
    }

}