package com.androidtutorials.androidhelloworld

import javax.inject.Inject
// Engine class is a dependency for Car
class Engine @Inject constructor() {
    // @Inject constructor():
    // ----------------------
    // 1. Tells Hilt how to create an instance of Engine.
    // 2. When Car requests Engine, Hilt knows it can build it.
    fun start():String{
        return "Engine Started"
    }
}