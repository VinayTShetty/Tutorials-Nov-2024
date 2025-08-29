package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// @AndroidEntryPoint:
// -------------------
// 1. Marks this Activity as a Hilt container.
// 2. Required on all Android components (Activity, Fragment, Service...)
//    where you want to use @Inject.
// 3. Hilt generates a subclass of MainActivity that performs injection automatically.
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    // Field Injection
    // ----------------
    // @Inject lateinit var car: Car
    // 1. Hilt will create a Car instance (with its Engine)
    //    and assign it to this field before onCreate() is called.

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