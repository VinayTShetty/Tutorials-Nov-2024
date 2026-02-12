package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private  val TAG= "WITH-CONTEXT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: Thread Name = "+ Thread.currentThread().name+ " ID = "+ Thread.currentThread().id)

        GlobalScope.launch (Dispatchers.Main){
            Log.d(TAG, "Inside  Global Scope Launch : Thread Name = "+ Thread.currentThread().name+ " ID = "+ Thread.currentThread().id)

            withContext(Dispatchers.IO){
                Log.d(TAG, "After Changing to WithContext  : Thread Name = "+ Thread.currentThread().name+ " ID = "+ Thread.currentThread().id)
            }
        }
        Log.d(TAG, "OutSideCoroutine : Thread Name = "+ Thread.currentThread().name+ " ID = "+ Thread.currentThread().id)
    }
}

/**
04:57:00.371 WITH-CONTEXT             D  onCreate: Thread Name = main ID = 2
04:57:00.426 WITH-CONTEXT             D  OutSideCoroutine : Thread Name = main ID = 2
04:57:00.791 WITH-CONTEXT             D  Inside  Global Scope Launch : Thread Name = main ID = 2
04:57:00.839 WITH-CONTEXT             D  After Changing to WithContext  : Thread Name = DefaultDispatcher-worker-1 ID = 59


 */