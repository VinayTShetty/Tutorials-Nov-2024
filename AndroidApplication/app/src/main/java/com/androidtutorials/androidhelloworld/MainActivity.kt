package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private val TAG=MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Executed")
        /**
         * Initialize ViewModel
         */
        var counterviewModelProvider: CounterViewModel=ViewModelProvider(this).get(CounterViewModel::class.java)

        val buttonClick=findViewById<Button>(R.id.btn_clickme)
        val countertext=findViewById<TextView>(R.id.text_message)
        /**
         * assign the textvalue here so that the counter value don t change during configuration.
         * because after executing again.It fetches the value from ViewModel and show to user.
         * Here also onCreate() is getting executed again.
         */
        countertext.text=counterviewModelProvider.number.toString()

        buttonClick.setOnClickListener {
            countertext.text=counterviewModelProvider.increaseCount().toString()
        }
    }
}