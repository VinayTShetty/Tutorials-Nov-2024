package com.androidtutorials.androidhelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

        companion object {
            private const val  TAG= "LifeCycleScope"
        }

    private  lateinit var  btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById<Button>(R.id.btn_mainAct)

        Log.d(TAG, "onCreate: In MainActivity")


        lifecycleScope.launch {
            Log.d(TAG, "MainActivity Corotuine Started")
            delay(5000)
            Log.d(TAG, "MainActivity Coroutine Finished")
        }

        btn.setOnClickListener {
            val intent = Intent(this, Activity_B::class.java)
            startActivity(intent)
            /**
             * Activity will be destroyed.
             * Removed from the backstack completely.
             * onDestory() will be called.
             */
            finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MainActivity Destroyed.")
    }
}