package com.androidtutorials.androidhelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOpenSend=findViewById<Button>(R.id.btnOpenSecond)
        buttonOpenSend.setOnClickListener {
            navigateToSecondAcvitiyt()
        }
    }

    private fun navigateToSecondAcvitiyt(){
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("name","Android")
        Log.d("INTENT_TAG", "Explicit Intent Created")
        startActivity(intent)
    }
}