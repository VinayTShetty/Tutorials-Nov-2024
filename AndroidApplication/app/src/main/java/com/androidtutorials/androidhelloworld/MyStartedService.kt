package com.androidtutorials.androidhelloworld

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

const val TAG="MyService"

class MyStartedService  : Service(){

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: Service Created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Service Started")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service Destroyed")
    }

     override fun onBind(p0: Intent?): IBinder? {
         /**
          * Starter Service does not support binding
          */
         return null
     }

 }