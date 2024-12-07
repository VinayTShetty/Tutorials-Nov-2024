package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private  var TAG=MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * make sure the button and counterText are intiated after the setContentView is called.
         */
        val button_Inc:Button=findViewById(R.id.incrementButton)
        val button_Dec:Button=findViewById(R.id.decrementButton)

        val counterText:TextView=findViewById(R.id.counterTextView)
        val counterViewModel= ViewModelProvider(this)[CounterViewModel::class.java]

        /**
         * count here is lambda value can be given any name.
         */
        counterViewModel.counter.observe(this, Observer {count->
            Log.d(TAG, "onCreate: ")
            counterText.text=count.toString()
        })

        button_Inc.setOnClickListener {
            counterViewModel.incrementCountervalue()
        }

        button_Dec.setOnClickListener {
            counterViewModel.decrementCountervalue()
        }
    }
}