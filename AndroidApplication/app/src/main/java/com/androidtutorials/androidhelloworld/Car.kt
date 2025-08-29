package com.androidtutorials.androidhelloworld

import javax.inject.Inject

// Car depends on Engine (it cannot work without Engine)
class Car @Inject constructor() {
    // @Inject constructor():
    // ----------------------
    // 1. Tells Hilt how to provide Car instances.
    // 2. Hilt will automatically inject Engine into Car.
    // 3. If Car had constructor parameters (e.g., Engine), Hilt would inject them automatically.

    // Method Injection Example
    private lateinit var _engine: Engine

    fun drive():String{
        // Uses Engine to perform an action
        return _engine.start()+" Car is Moving"
    }

    // @Inject on a method → Method Injection
    // --------------------------------------
    // 1. Hilt will automatically call this method AFTER creating the Car object.
    // 2. It will pass Engine as an argument.
    // 3. This is useful for setting dependencies that cannot be passed in the constructor
    //    or for doing additional initialization.
    @Inject
    fun setEngine(engine: Engine){
        println("✅ setEngine() was called by Hilt!")
        this._engine=engine
    }
}