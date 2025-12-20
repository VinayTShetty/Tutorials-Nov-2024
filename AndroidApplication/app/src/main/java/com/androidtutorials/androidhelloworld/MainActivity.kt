package com.androidtutorials.androidhelloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity
 * ------------
 * - Starts Foreground Service
 * - Stops Foreground Service
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity Created")

        val startBtn = findViewById<Button>(R.id.btnStartService)
        val stopBtn = findViewById<Button>(R.id.btnStopService)

        // Start Foreground Service
        startBtn.setOnClickListener {
            Log.d(TAG, "Start Service Button Clicked")
            val intent = Intent(this, MyForegroundService::class.java)
            startForegroundService(intent)
        }

        // Stop Foreground Service
        stopBtn.setOnClickListener {
            Log.d(TAG, "Stop Service Button Clicked")
            val intent = Intent(this, MyForegroundService::class.java)
            stopService(intent)
        }
    }
}
