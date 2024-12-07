package com.androidtutorials.androidhelloworld

import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
        var number=0

        fun increaseCount():Int{
                ++number
            return number
        }
}