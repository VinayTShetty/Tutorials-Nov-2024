package com.androidtutorials.androidhelloworld

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AlaramReceiever : BroadcastReceiver() {

    private val TAG = AppConstants.TAG_ALARM_RECEIVER

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "onReceive: Alaram Triggered OnReceive Called")

        context ?: return

        val notificationManager = getNotificationManager(context)
        createNotificationChannel(notificationManager)
        val notification = buildNotification(context)

        showNotification(notificationManager, notification)
    }

    /**
     * Step 1 : Create PendingIntent to open app on notification click
     */
    private fun getContentPendingIntent(context: Context): PendingIntent {

        val openIntent = Intent(context, MainActivity::class.java)

        return PendingIntent.getActivity(
            context,
            0,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    /**
     * Step 2 : Get NotificationManager
     */
    private fun getNotificationManager(context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    /**
     * Step 3 : Create Notification Channel (Android 8+)
     */
    private fun createNotificationChannel(notificationManager: NotificationManager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                AppConstants.NOTIFICATION_CHANNEL_ID,
                AppConstants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(channel)
            Log.d(TAG, "Notification Channel Created")
        }
    }

    /**
     * Step 4 : Build Notification
     */
    private fun buildNotification(context: Context) = NotificationCompat.Builder(
        context,
        AppConstants.NOTIFICATION_CHANNEL_ID
    )
        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
        .setContentTitle(AppConstants.NOTIFICATION_TITLE)
        .setContentText(AppConstants.NOTIFICATION_MESSAGE)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .setContentIntent(getContentPendingIntent(context))
        .build()

    /**
     * Step 5 : Show Notification
     */
    private fun showNotification(
        notificationManager: NotificationManager,
        notification: android.app.Notification
    ) {
        notificationManager.notify(
            AppConstants.NOTIFICATION_ID,
            notification
        )
        Log.d(TAG, "Notification Displayed")
    }
}
