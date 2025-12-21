package com.androidtutorials.androidhelloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BatteryReceiver : BroadcastReceiver() {

    // Individual Receiver Tag
    private val RECEIVER_TAG = "Battery-Receiver"

    // Common Tag (same as Activity)
    private val COMMON_TAG = "Common-Life-Cycle"

    override fun onReceive(context: Context?, intent: Intent?) {

        // This method is called by the SYSTEM
        // even when the app is not running

        when (intent?.action) {

            // Triggered when battery becomes low (~15%)
            Intent.ACTION_BATTERY_LOW -> {
                Log.d(COMMON_TAG, "Battery Broadcast Received")
                Log.d(RECEIVER_TAG, "BATTERY LOW")
            }

            // Triggered when battery recovers from low state
            Intent.ACTION_BATTERY_OKAY -> {
                Log.d(COMMON_TAG, "Battery Broadcast Received")
                Log.d(RECEIVER_TAG, "BATTERY OKAY")
            }
        }
    }
}
