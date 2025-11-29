
💡 What are Dispatchers?

Dispatchers decide which thread or thread pool a coroutine runs on.
They control execution context — essential for performance and thread safety


Types of Dispatchers:-
1️⃣ Dispatchers.Main 
2️⃣ Dispatchers.IO
3️⃣ Dispatchers.Default

extra Dispatchers.Unconfined


1️⃣ Dispatchers.Main 

Used for UI-related tasks
Runs on the Main (UI) Thread

Only available on platforms with a UI:

Android
JavaFX
Swing


2️⃣ Dispatchers.IO

For I/O operations like:
✔ Reading/Writing files
✔ Network requests
✔ Database operations

Optimized thread pool size for blocking tasks


3️⃣ Dispatchers.Default

For CPU-intensive operations like:
✔ Sorting large lists
✔ Complex computations

Uses shared background threads equal to CPU cores

********************************************************************************************************************
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview = findViewById<TextView>(R.id.myTextview)

        GlobalScope.launch (Dispatchers.Main) {

            textview.text="Hello Coroutines =  ${Thread.currentThread().name}"
            delay(2000)
            textview.text="Updating Using Dispatcher.Main =  ${Thread.currentThread().name}"
        }
    }
}


<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/myTextview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

********************************************************************************************************************


Extra: Dispatchers.Unconfined
*****************************
Starts in caller thread but can resume in a different thread

🔥 Rarely used — unpredictable thread behavior!

GlobalScope.launch(Dispatchers.Unconfined) {
    println("Running on thread: ${Thread.currentThread().name}")
}



package com.androidtutorials.androidhelloworld


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "CoroutineTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview = findViewById<TextView>(R.id.myTextview)

        GlobalScope.launch (Dispatchers.Unconfined) {
            before()
            after()
        }

        Thread.sleep(5000) // Not Recomended just for the Demo
    }

    private suspend fun before(){
        repeat(5){i->
            Log.d(TAG, "Before Counter $i → Thread = ${Thread.currentThread().name}")
        }
        delay(2000) // <-- Suspension point triggers thread change
    }

    private suspend fun after(){
        repeat(5){i->
            Log.d(TAG, "After Counter $i → Thread = ${Thread.currentThread().name}")
        }
        delay(2000)
    }
}





13:47:40.753 CoroutineTest            D  Before Counter 0 → Thread = main
13:47:40.753 CoroutineTest            D  Before Counter 1 → Thread = main
13:47:40.753 CoroutineTest            D  Before Counter 2 → Thread = main
13:47:40.753 CoroutineTest            D  Before Counter 3 → Thread = main
13:47:40.753 CoroutineTest            D  Before Counter 4 → Thread = main
13:47:42.759 CoroutineTest            D  After Counter 0 → Thread = kotlinx.coroutines.DefaultExecutor
13:47:42.759 CoroutineTest            D  After Counter 1 → Thread = kotlinx.coroutines.DefaultExecutor
13:47:42.759 CoroutineTest            D  After Counter 2 → Thread = kotlinx.coroutines.DefaultExecutor
13:47:42.759 CoroutineTest            D  After Counter 3 → Thread = kotlinx.coroutines.DefaultExecutor
13:47:42.759 CoroutineTest            D  After Counter 4 → Thread = kotlinx.coroutines.DefaultExecutor

*****************************************************************************************************************
🎯 Why We Use withContext()

withContext() allows us to switch Dispatchers inside a running coroutine.


For Example Swithcing between tasks

withContext() allows us to switch Dispatchers inside a running coroutine.

| Task                           | Dispatcher            |
| ------------------------------ | --------------------- |
| UI updates                     | `Dispatchers.Main`    |
| Network / DB / File operations | `Dispatchers.IO`      |
| CPU-heavy tasks                | `Dispatchers.Default` |

✔ The app remains smooth
✔ UI thread never blocks
✔ Background work runs efficiently


Programe :- 

package com.androidtutorials.androidhelloworld


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val TAG = "CoroutineTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.myTextview)

        GlobalScope.launch (Dispatchers.Main) {
            textView.text="Fetching Data Thread Name =${Thread.currentThread().name}"

            //Switch to IO Thread for Long Running Task
            val result= withContext(Dispatchers.IO){
                fetchUserData()
            }

          /*  Back to MainThread /UI Thread for changing the view.
              Automatically switch to Main Thread.
              After coming out from Context
          */

            textView.text="result $result"
            Log.d(TAG, " UI updated on ${Thread.currentThread().name}")

        }
    }

    suspend fun fetchUserData() : String{
        Log.d(TAG, "fetchUserData: ${Thread.currentThread().name}")
        delay(3000)
        return "Vinay T S"
    }
}

16:09:05.497 CoroutineTest            D  fetchUserData: DefaultDispatcher-worker-1
16:09:08.509 CoroutineTest            D   UI updated on main

*****************************************************************************************************************