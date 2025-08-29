package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// @AndroidEntryPoint:
// -------------------
// 1. Must be added to every Android component (Activity, Fragment, Service, etc.)
//    where we want to use @Inject fields.
// 2. It tells Hilt: "This is a DI container entry point.
//    Provide dependencies here."
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Field Injection
    // ----------------
    // @Inject lateinit var car: Car
    // 1. We request Hilt to inject Car here.
    // 2. Hilt will create a Car instance (along with its Engine)
    //    and assign it automatically.

    @Inject
    lateinit var car :Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = TextView(this)
        // Now car is automatically provided by Hilt
        textView.text = car.drive() // Will print: "Engine started... Car is moving..."

        setContentView(textView)

    }
}