package com.androidtutorials.androidhelloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "ACTIVITY_RESULT"

    /*
    STEP 1:
    Register Activity Result Launcher
    --------------------------------
    • This replaces startActivityForResult()
    • It listens for result coming back
    • Lifecycle-aware (safe)
    */
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            Log.d(TAG, "Result received from SecondActivity")

            /*
            STEP 5:
            Check if result is OK
            */
            if (result.resultCode == RESULT_OK) {

                /*
                STEP 6:
                Extract data from result intent
                */
                val data = result.data
                val message = data?.getStringExtra("result_key")

                Log.d(TAG, "Result Data: $message")

                /*
                STEP 7:
                Display result in TextView
                */
                findViewById<TextView>(R.id.txtResult).text = message
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpen = findViewById<Button>(R.id.btnOpen)

        /*
        STEP 2:
        Button click opens SecondActivity
        */
        btnOpen.setOnClickListener {

            Log.d(TAG, "Opening SecondActivity")

            /*
            STEP 3:
            Create Explicit Intent
            */
            val intent = Intent(this, SecondActivity::class.java)

            /*
            STEP 4:
            Launch activity using Activity Result API
            */
            activityResultLauncher.launch(intent)
        }
    }
}
