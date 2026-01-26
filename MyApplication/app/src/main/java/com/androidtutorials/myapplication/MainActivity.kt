package com.androidtutorials.myapplication

/*******************************************************
 * IMPORTS – REQUIRED CLASSES
 *******************************************************/
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

/*******************************************************
 * MAIN ACTIVITY
 *
 * • Entry point of the app
 * • Hosts Jetpack Compose UI
 *******************************************************/
class MainActivity : ComponentActivity() {

    /*******************************************************
     * COMPANION OBJECT
     *
     * • Holds constants
     * • Prevents hardcoding
     *******************************************************/
    companion object {
        private const val TAG = "NotificationDemo"

        // Unique channel identifier
        private const val CHANNEL_ID = "demo_channel"

        // Unique notification ID
        private const val NOTIFICATION_ID = 1001

        // Permission request identifier
        private const val PERMISSION_REQUEST_CODE = 2001
    }

    /*******************************************************
     * onCreate()
     *
     * • First lifecycle method
     * • Called when activity is created
     *******************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Activity Created")

        // Business Logic Step 1:
        // Ask user permission for notifications (Android 13+)
        requestNotificationPermission()

        // Business Logic Step 2:
        // Create notification channel (Android 8+)
        createNotificationChannel()

        // Business Logic Step 3:
        // Load Compose UI
        setContent {
            NotificationButton()
        }
    }

    /*******************************************************
     * REQUEST NOTIFICATION PERMISSION
     *
     * • Only needed for API 33+
     *******************************************************/
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val permissionGranted =
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

            if (!permissionGranted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    /*******************************************************
     * PERMISSION CALLBACK
     *
     * • Called automatically by system
     *******************************************************/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "Notification Permission Granted")
            } else {
                Log.d(TAG, "Notification Permission Denied")
            }
        }
    }

    /*******************************************************
     * CREATE NOTIFICATION CHANNEL
     *
     * • Mandatory for Android 8+
     *******************************************************/
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Demo Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel used for demo notifications"
            }

            val manager =
                getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }

    /*******************************************************
     * SHOW NOTIFICATION
     *
     * • Builds notification
     * • Sends it to Android system
     *******************************************************/
    private fun showNotification(context: Context) {

        // Check system-level notification enablement
        if (!NotificationManagerCompat
                .from(context)
                .areNotificationsEnabled()
        ) {
            Log.d(TAG, "Notifications Disabled by User")
            return
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_mail_24) // Mandatory
            .setContentTitle("Hello 👋")
            .setContentText("Notification from Jetpack Compose")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context)
            .notify(NOTIFICATION_ID, notification)
    }

    /*******************************************************
     * COMPOSE UI
     *
     * • Button triggers notification
     *******************************************************/
    @Composable
    fun NotificationButton() {

        val context = LocalContext.current

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Button(
                onClick = {
                    showNotification(context)
                }
            ) {
                Text(text = "Show Notification")
            }
        }

    }
}

/*******************************************************
 * FINAL BUSINESS LOGIC SUMMARY
 *
 * • App starts → onCreate()
 * • Permission requested (API 33+)
 * • Channel created (API 26+)
 * • User clicks button
 * • Notification built & shown
 *
 *******************************************************/
