package com.androidtutorials.androidhelloworld

import javax.inject.Inject

// Car depends on Engine
class Car @Inject constructor(private val engine: Engine) {
    // @Inject constructor():
    // ----------------------
    // 1. Tells Hilt how to provide Car instances.
    // 2. Hilt will automatically inject Engine into Car.

    fun drive():String{
        // Uses Engine to perform an action
        return engine.start()+" Car is Moving"
    }
}