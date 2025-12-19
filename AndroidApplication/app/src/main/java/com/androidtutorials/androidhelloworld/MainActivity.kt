package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * @AndroidEntryPoint
 * ➜ Makes this Activity a Hilt container
 * ➜ Allows field injection
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var calculator : Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = calculator.add(10,20)
        println("Addition of 2 Numbers = $result")
    }
}