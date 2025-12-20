package com.androidtutorials.androidhelloworld

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

/**
 * Foreground Service
 * ------------------
 * - Runs in background
 * - MUST show a notification
 * - Has high priority
 * - Suitable for music, GPS, downloads
 */
class MyForegroundService : Service() {

    companion object {
        const val TAG = "MyForegroundService"
        const val CHANNEL_ID = "FOREGROUND_SERVICE_CHANNEL"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service Created")
        // Required for Android 8+ (Oreo and above)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service Started")

        // Build notification using separate method
        val notification = buildNotification()

        /**
         * startForeground()
         * -----------------
         * - Converts normal service into Foreground Service
         * - Notification MUST be shown
         * - Service gets higher priority
         */
        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Not a bound service
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service Destroyed")

        /**
         * stopForeground(true)
         * --------------------
         * - Stops foreground state
         * - Removes notification
         */
        stopForeground(true)

        // Extra safety: explicitly cancel notification
        val manager = getSystemService(NotificationManager::class.java)
        manager.cancel(NOTIFICATION_ID)
    }

    /**
     * Notification Channel
     * --------------------
     * Required for Android Oreo (API 26+) and above
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)

            Log.d(TAG, "Notification Channel Created")
        }
    }

    /**
     * Builds the notification shown to the user
     * ------------------------------------------
     * - Mandatory for Foreground Service
     * - Visible while service is running
     */
    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Service is running in background")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true) // Cannot be swiped away
            .build()
    }
}
