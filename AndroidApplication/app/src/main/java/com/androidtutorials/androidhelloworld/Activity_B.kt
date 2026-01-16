package com.androidtutorials.androidhelloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_B : AppCompatActivity() {
    
    companion object{

        private const val TAG= "LifeCycleScope"

    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        Log.d(TAG, "onCreate: Activity_B")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity_B")
    }
}