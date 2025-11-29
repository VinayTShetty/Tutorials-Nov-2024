Important Links:-https://chatgpt.com/c/692a4143-8cc4-8323-9dd6-95f6b7ec5418
Official Documentation:-https://kotlinlang.org/docs/flow.html




What is Kotlin Flow? (Definition)
*********************************
Kotlin Flow is a component of Kotlin Coroutines used for handling asynchronous data streams.
It emits multiple values over time,
similar to an Rx stream, but built for Kotlin + Coroutines.

One value → suspend function
Multiple values over time → Flow

✅ Why Kotlin Flow is Used?
******************************

Flow is used when you need:

✔ Asynchronous stream of data :-
  (e.g., reading updates from database, network, sensors)

✔ Continuous or repeated emission :-
   Events, data changes, timers, countdowns, network polling.

✔ More control than LiveData :-
  Transformers, operators, exception handling, backpressure support.
 
✔ Clean, modern, Coroutine-friendly API :-
  Flow fully integrates with suspend, CoroutineScope, structured concurrency.


🔄 How Flow Works Internally
******************************

Flow works in 3 steps:

1️⃣ Producer
flow { emit(...) } produces values.

2️⃣ Intermediaries
map, filter, debounce, etc.

3️⃣ Collector
collect { } receives values.


✅ When Kotlin Flow is Used?
******************************
Use a Flow when:

✔ Data changes over time
    Room database changes (Flow built-in with Room)
    Form validation updates
    UI state updates
    Stream of user actions

✔ You need background & asynchronous work
    Fetch new data every X seconds
    Listen to network status
    Observe preferences (DataStore uses Flow)

✔ You want reactive programming in Kotlin
    But with Coroutines instead of RxJava.

💡 Why Choose Flow Over LiveData, Coroutines, WorkManager?    
***********************************************************
📌 1. Flow vs LiveData
 
| Feature               | LiveData  | Flow                           |
| --------------------- | --------- | -----------------------------  |
| Lifecycle-aware       | ✔ Yes     | ❌ No (use StateFlow for that)|
| Background operations | ❌ Limited | ✔ Fully coroutine-based      |
| Transformations       | Limited   | ✔ Powerful operators.          |
| Thread control        | Hard      | Easy (`flowOn`)                |
| UI-only               | ✔ Yes     | ❌ Backend + UI               |


👉 Use LiveData for simple UI updates
👉 Use Flow for full reactive async programming



📌 2. Flow vs Coroutines

A coroutine handles one result.
Flow handles many results over time.

| Need                | Use       |
| ------------------- | --------- |
| Return single value | `suspend` |
| Stream of values    | `Flow`    |


📌 3. Flow vs WorkManager

WorkManager is for deferrable background tasks:
 Uploading images
 Syncing data
 Work that should continue even if app closes


 | Use Case                             | WorkManager | Flow |
| ------------------------------------- | ----------- | ---- |
| Long background work                  | ✔           | ❌  |
| Periodic tasks even after killing app | ✔           | ❌  |
| UI updates                            | ❌          | ✔   |
| Live data streams                     | ❌          | ✔   |


    
✅ Basic Syntax of Flow    
******************************

// Create a Flow
val flow = flow {
    emit(1)
    emit(2)
    emit(3)
}

// Collect the Flow
flow.collect { value ->
    println(value)
}

Flow is cold 
Nothing happens until collect() is called.

Meaning of “Nothing Happens” in Flow

A cold flow does not start producing any values until someone collects it.

✔ No code inside flow { } runs
✔ No values are emitted
✔ No coroutine is started
✔ No memory or CPU is used
✔ No side effects happen
✔ No work is triggered

The whole flow is completely idle until collect() is executed.

***********************************************************************************************
✅ Example of Kotlin Flow
**************************
package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "FLOWS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Simple Flow Example (Flow itself is cold and does not run until collected)
        val numberflow = flow {
            for (i in 1..5)
                emit(i)  // Emit value
                delay(1000) // Suspend for 1 second
        }
        /*
         * IMPORTANT:
         * Collecting a Flow must happen inside a CoroutineScope.
         *
         * Why?
         * - collect() is a suspend function → it can only run inside a coroutine.
         * - Flow is cold → it does not start emitting until collect() is called.
         * - When we launch a coroutine (using lifecycleScope.launch), we provide a
         *   coroutine context in which the Flow can execute and emit values.
         *
         * In summary: Defining a Flow does NOT need a CoroutineScope,
         * but executing (collecting) a Flow REQUIRES a CoroutineScope.
         */
        lifecycleScope.launch {
            numberflow.collect { value ->
                Log.d(TAG, "Received $value")
            }
        }
    }

}
***********************************************************************************************
✅ FLOW BUILDERS 
*****************

Kotlin provides several standard builders to create flows:

1️⃣ flow { }
2️⃣ flowOf()
3️⃣ asFlow()
4️⃣ callbackFlow { }
5️⃣ channelFlow { }



1️⃣ flow { } :- The Standard Flow Builder
***********
✔ Used to create a cold flow
✔ Can use suspending functions like delay()
✔ Best for sequential emission

package com.androidtutorials.androidhelloworld
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {
    private val TAG = "FLowBuilder"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val numberflow = flow {
            for (i in 1..5)
                emit(i)
                delay(1000)
        }
        lifecycleScope.launch {
            numberflow.collect { value ->
                Log.d(TAG, "received flow $value")
            }
        }
    }
}
********************************************************************************
2️⃣ flowOf() — Creates Flow from Fixed Values
✔ No suspending functions
✔ Simple and fast
✔ Used for static / small values


package com.androidtutorials.androidhelloworld
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {
    private val TAG = "FlowBuilder"
    val namesFlow = flowOf("A", "B", "C")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
                namesFlow.collect { value ->
                Log.d(TAG, "Flow Of flow $value")
            }
        }
    }
}

*******************************************************************************
3️⃣ asFlow() — Converts Collections to Flow

✔ Converts lists, ranges, arrays into Flow
✔ Emits items one by one

package com.androidtutorials.androidhelloworld
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
class MainActivity : AppCompatActivity() {
    private val TAG = "FlowBuilder"
    val listFlow = listOf(10, 20, 30).asFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            listFlow.collect { value ->
                Log.d(TAG, "As Flow $value")
            }
        }
    }
}


*******************************************************************************

4️⃣ callbackFlow { } — Convert Callbacks into Flow

✔ For asynchronous listener-based APIs
✔ Must use trySend(value) to emit
✔ Always ends with awaitClose { }


package com.androidtutorials.androidhelloworld
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "CallBackFlow"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch { 
            getBatteryUpdates().collect { level ->
                Log.d(TAG, "onCreate: $level")
            }
        }
    }
    private fun getBatteryUpdates()= callbackFlow<Int>{

        val receiver = BatteryReceiver()
        //set listner
        receiver.listener= object : BatteryListener{
            override fun batteryLevelChanged(level: Int) {
                trySend(level)
            }
        }
        // register receiver
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(receiver,intentFilter)

        //UnregisterReceiver
        awaitClose {
            unregisterReceiver(receiver)
        }
    }
}

------------------------
package com.androidtutorials.androidhelloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryReceiver : BroadcastReceiver () {
    var listener : BatteryListener ?=null

    override fun onReceive(context: Context?, intent: Intent?) {
        val level =intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,-1) ?: -1
        listener?.batteryLevelChanged(level)
    }
}
------------------------------
package com.androidtutorials.androidhelloworld

interface BatteryListener {
    fun batteryLevelChanged(level :Int)
}
-------------------------------------
***************************************************************************************************

5️⃣ channelFlow { } — Concurrent / Parallel Flow

✔ Allows multiple coroutines to emit values
✔ Good for parallel tasks
✔ Faster than normal flow


package com.androidtutorials.androidhelloworld
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "channelFlow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Collect the flow
        lifecycleScope.launch {
            getParallelFlow().collect { result ->
                Log.d(TAG, "onCreate: $result")
            }
        }
    }


    fun getParallelFlow()= channelFlow {

        launch {
            delay(500)
            send("Task 1 Done")
        }

        launch {
            delay(500)
            send("Task 2 Done")
        }
    }
}
***************************************************************************************************
