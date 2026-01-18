package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private  val TAG="DISPATCHERS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch (Dispatchers.Main) {
            Log.d(TAG, " Gloabal Scope Launch --> Thread Name= "+ Thread.currentThread().name+" ID = "+ Thread.currentThread().id)
        }

        GlobalScope.async (Dispatchers.Main) {
            Log.d(TAG, " Gloabal Scope async --> Thread Name= "+ Thread.currentThread().name+" ID = "+ Thread.currentThread().id)
        }
    }
}