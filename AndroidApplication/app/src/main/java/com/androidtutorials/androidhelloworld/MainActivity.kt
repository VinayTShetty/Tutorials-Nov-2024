package com.androidtutorials.androidhelloworld

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = AppConstants.TAG_ALARM_MANAGER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Set Alaram Button Clicked")

        scheduleAlarm()
    }

    /**
     * STEP 1–5 : Schedule Alarm
     */
    private fun scheduleAlarm() {

        val alarmManager = getAlarmManager()
        val pendingIntent = getAlarmPendingIntent()

        /**
         * Step 4 : Set trigger time (10 Seconds Later)
         */
        val triggerTime = System.currentTimeMillis() + 10_000

        /**
         * Step 5 : Schedule the exact alarm
         */
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent
        )
    }

    /**
     * STEP 1 : Get AlarmManager system service
     */
    private fun getAlarmManager(): AlarmManager {
        return getSystemService(ALARM_SERVICE) as AlarmManager
    }

    /**
     * STEP 2–3 : Create PendingIntent for BroadcastReceiver
     */
    private fun getAlarmPendingIntent(): PendingIntent {

        val intent = Intent(this, AlaramReceiever::class.java)

        return PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
