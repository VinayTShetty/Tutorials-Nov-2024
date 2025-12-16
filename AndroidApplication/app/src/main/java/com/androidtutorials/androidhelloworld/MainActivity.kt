package com.androidtutorials.androidhelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intialializeService()

        val btnStart=findViewById<Button>(R.id.btnStartService)
        val btnStop=findViewById<Button>(R.id.btnStopService)

        btnStart.setOnClickListener {
            startService()
        }

        btnStop.setOnClickListener {
            stopService()
        }
    }

    fun intialializeService(){
        serviceIntent= Intent(this, MyStartedService::class.java)
    }

    fun startService(){
        startService(serviceIntent)
    }

    fun stopService(){
        stopService(serviceIntent)
    }
}