package com.androidtutorials.androidhelloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SeconActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d(AppConstants.TAG, "SeconActivity OnCreate")

    }
}