package com.androidtutorials.androidhelloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Common Tag (for both Activity & Fragment)
    private val COMMON_TAG = "CommonLifeCycle"

    // Individual Activity Tag
    private val ACTIVITY_TAG = "ActivityLifeCycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(COMMON_TAG, "Activity - onCreate")
        Log.d(ACTIVITY_TAG, "onCreate")

        // Load Fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SampleFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(COMMON_TAG, "Activity - onStart")
        Log.d(ACTIVITY_TAG, "onStart")
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(COMMON_TAG, "Activity - onRestart")
        Log.d(ACTIVITY_TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(COMMON_TAG, "Activity - onResume")
        Log.d(ACTIVITY_TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(COMMON_TAG, "Activity - onPause")
        Log.d(ACTIVITY_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(COMMON_TAG, "Activity - onStop")
        Log.d(ACTIVITY_TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(COMMON_TAG, "Activity - onDestroy")
        Log.d(ACTIVITY_TAG, "onDestroy")
    }
}
