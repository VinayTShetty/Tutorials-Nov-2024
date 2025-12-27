package com.androidtutorials.androidhelloworld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Tag used for Logcat filtering
    private val TAG = "IMPLICIT_INTENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        STEP 0:
        Attach XML layout to this Activity
        */
        setContentView(R.layout.activity_main)

        /*
        STEP 1:
        Get reference of Button from XML
        */
        val btnOpenDialer = findViewById<Button>(R.id.btnOpenDialer)

        /*
        STEP 2:
        Set click listener on Button
        */
        btnOpenDialer.setOnClickListener {

            Log.d(TAG, "Button clicked")

            /*
            STEP 3:
            Create Implicit Intent
            ACTION_DIAL → Open Dialer app
            */
            val intent = Intent(Intent.ACTION_DIAL)

            /*
            STEP 4:
            Attach phone number using tel: scheme
            */
            intent.data = Uri.parse("tel:0000000000")

            Log.d(TAG, "Dialer Intent Created with number")

            /*
            STEP 5:
            Start activity
            Android system decides which app to open
            */
            startActivity(intent)

            Log.d(TAG, "Dialer Activity Started")
        }
    }
}
