package com.androidtutorials.androidhelloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    private val TAG = "ACTIVITY_RESULT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnSendResult = findViewById<Button>(R.id.btnSendResult)

        /*
        STEP 8:
        Button click sends result back
        */
        btnSendResult.setOnClickListener {

            Log.d(TAG, "Sending result back to MainActivity")

            /*
            STEP 9:
            Create Intent to hold result data
            */
            val randomNumber = Random.nextInt(1, 100)
            val resultIntent = Intent()
            resultIntent.putExtra(
                "result_key",
                "Generated Random Number $randomNumber"
            )

            /*
            STEP 10:
            Set result and finish activity
            */
            setResult(RESULT_OK, resultIntent)
            /*
    finish()
    --------
    • Closes the current Activity
    • Removes it from the back stack
    • Returns control to the previous Activity
    • Triggers result delivery (if setResult() was called)

   Important Point
      finish() is MANDATORY when returning result.
    */
            finish()
        }
    }
}
