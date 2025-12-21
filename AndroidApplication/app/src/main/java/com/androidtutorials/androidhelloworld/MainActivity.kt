package com.androidtutorials.androidhelloworld

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.net.ConnectivityManager

class MainActivity : AppCompatActivity() {

    private val ACTIVITY_TAG = "Activity-Life-Cycle"
    private val COMMON_TAG = "Common-Life-Cycle"

    private lateinit var wifiReceiver: WifiReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(ACTIVITY_TAG, "Activity - onCreate")
        Log.d(COMMON_TAG, "onCreate")

        wifiReceiver = WifiReceiver()
    }

    override fun onStart() {
        super.onStart()

        val intentFilter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }

        registerReceiver(wifiReceiver, intentFilter)

        Log.d(COMMON_TAG, "WiFi Receiver Registered")
        Log.d(ACTIVITY_TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(wifiReceiver)

        Log.d(COMMON_TAG, "WiFi Receiver UN-Registered")
        Log.d(ACTIVITY_TAG, "onStop")
    }
}
