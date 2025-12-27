package com.androidtutorials.androidhelloworld

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.androidtutorials.androidhelloworld.R.id.textView_receiveData

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        
        val txtname = findViewById<TextView>(textView_receiveData)
        
        val receivedName=intent.getStringExtra("name")
        txtname.text=receivedName
        Log.d("INTENT_TAG", "Received Data $receivedName")
    }
    
    
    
}