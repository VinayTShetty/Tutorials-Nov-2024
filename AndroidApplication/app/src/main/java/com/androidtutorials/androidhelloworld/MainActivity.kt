package com.androidtutorials.androidhelloworld

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val logic = MyLogic()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(AppContansts.TAG, "MainActivity onCreate")

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            Log.d(AppContansts.TAG, "Start Button Clicked")
            logic.startWork()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            Log.d(AppContansts.TAG, "Cancel Button Clicked")
            logic.cancelWork()
        }
    }
}
