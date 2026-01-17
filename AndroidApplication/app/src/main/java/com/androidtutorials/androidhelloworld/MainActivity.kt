package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var logic: MyLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logic= MyLogic()

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            logic.startWork()
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            logic.cancelWork()
        }
    }
}