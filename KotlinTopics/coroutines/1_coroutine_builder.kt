Coroutine
*********

How to Write a Coroutine Builder in Kotlin





Normal Function execution.Application waits for the Thread to finish its work.
Then the application terminates.

Programe :- 
************

fun main() {
    println("Main Function")
    normalFunction()

}


fun normalFunction() {
    println("Before Sleeping")
    Thread.sleep(1000)
    println("After Sleeping")
}

C:\Users\vinayts\.jdks\corretto-1.8.0_312\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=50368:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\charsets.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\access-bridge-64.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\cldrdata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\dnsns.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jaccess.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\jfxrt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\localedata.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\nashorn.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunec.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunjce_provider.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunmscapi.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\sunpkcs11.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\ext\zipfs.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jce.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfr.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jfxswt.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\jsse.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\management-agent.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\resources.jar;C:\Users\vinayts\.jdks\corretto-1.8.0_312\jre\lib\rt.jar;D:\Workspace\kotlincode\build\classes\kotlin\main;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-android\1.3.9\df17db5e329363c4e9cc7bf5b661ce3723a3e460\kotlinx-coroutines-android-1.3.9.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib\1.7.10\d2abf9e77736acc4450dc4a3f707fa2c10f5099d\kotlin-stdlib-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.7.10\bac80c520d0a9e3f3673bc2658c6ed02ef45a76a\kotlin-stdlib-common-1.7.10.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains\annotations\13.0\919f0dfe192fb4e063e7dacadee7f8bb9a2672a9\annotations-13.0.jar;C:\Users\vinayts\.gradle\caches\modules-2\files-2.1\org.jetbrains.kotlinx\kotlinx-coroutines-core-jvm\1.3.9\4be434f5e86c1998a273e7f19a7286440894f0b0\kotlinx-coroutines-core-jvm-1.3.9.jar CheckLogicKt
Main Function
Before Sleeping
After Sleeping

Process finished with exit code 0


Suspend normalFunction
**********************
In Coroutine, suspend function execution.Application does not wait for the Coroutine to finish its work.
The application continues its work and may terminate before the Coroutine finishes its work.


import kotlinx.coroutines.*

fun main() {
    println("Main Function Started")

    GlobalScope.launch {   // Launching a coroutine
        suspendFunction()
    }

    println("Main Function Ended") // Main does not wait
}

suspend fun suspendFunction() {
    println("Before Delay")
    delay(1000)  // Non-blocking pause
    println("After Delay")
}

Output :-
*********
Main Function Started
Main Function Ended

Process finished with exit code 0



✔ Root Cause

The main thread is the host of your program.
When the main thread completes, the entire application terminates — including all child/background coroutines.


| Step | Event                                                      |
| ---- | ---------------------------------------------------------- |
| 1    | Main thread starts                                         |
| 2    | Coroutine starts on background                             |
| 3    | Main continues → prints "Main Function Ended"              |
| 4    | Main thread finishes → program ends                        |
| 5    | Background coroutine never gets time to resume after delay |

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
what is a suspend function ?
****************************
A suspend function is a special function that can be paused and resumed later without blocking the thread.

It is used for:
✔ Long-running tasks
✔ Network calls
✔ Database operations
✔ Delays & timers
💡 It only runs inside a coroutine.  // Given example later.


how to write a suspend function ?
********************************

fun normalFunction(){  // normal function.
    println("hello ")
}

suspend fun suspendFunction(){  // suspend function.
    println("hello ")
}


what is the use of the suspend function 
****************************************
A suspend function is a special function that can be paused and resumed later without blocking the thread.


How can we call a suspend function ?
************************************
⭐ How can we call a suspend function?

A suspend function cannot run by itself.
It MUST be called:

✔ From another suspend function
✔ Inside a coroutine (using a coroutine builder)


Normal Calling
**************
import kotlinx.coroutines.*

fun main() {
    println("Main Function")
    suspendFunctionExample()
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")
}


Output Error :-
e: D:\Workspace\kotlincode\src\main\kotlin\CheckLogic.kt: (5, 5): Suspend function 'suspendFunctionExample' should be called only from a coroutine or another suspend function

Calling Via Coroutine Builder
*****************************

import kotlinx.coroutines.*

fun main() {
    println("Main Function")
        runBlocking {
            suspendFunctionExample()
        }
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")
}

Output :-
Main Function
Process finished with exit code 0

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
Coroutine Builders
*******************



| Builder       |
| ------------- |
| `launch`      |
| `async`       |
| `runBlocking` |



Coroutine Builder Helper → Switch thread inside coroutine
*********************************************************

| `withContext` |


| Builder       | Needs Parent Scope?                            |
| ------------- | ---------------------------------------------  | 
| `launch`      | ✔ Yes                                          |
| `async`       | ✔ Yes                                          |
| `runBlocking` | ❌ Creates its **own scope**                   |
| `withContext` | ❌ Must be inside an already running coroutine |


Executing coroutine/suspend fucntions using Builders
******************************************************

launch/async builder
********************
Error :- using launch builder directly in main function without a CoroutineScope ,
Similarly do it for async builder also.

import kotlinx.coroutines.launch
fun main() {
    println("Main Function")
            launch{
            suspendFunctionExample()
        }
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")
}


runBlocking builder
********************
import kotlinx.coroutines.runBlocking

fun main() {
    println("Main Function")
            runBlocking{
            suspendFunctionExample()
        }
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")
}

Main Function
Suspend Function
Process finished with exit code 0
----------------------------------------------------------------------------------------------------------------------------------------------------------------------

Types of Scopes
***************

| Scope             | Quick Meaning                                            |
| ----------------- | -------------------------------------------------------- |
| `GlobalScope`     | “Runs forever… even if screen/activity dies” — avoid ❌  |
| `runBlocking`     | “Block main thread and wait” — only for learning/testing |
| `CoroutineScope`  | You create and cancel manually                           |
| `viewModelScope`  | Stops when ViewModel stops — recommended for UI ✔        |
| `lifecycleScope`  | Stops when Activity/Fragment destroyed — recommended ✔   |
| `supervisorScope` | Child failures won’t cancel siblings                     |
| `MainScope()`     | Good for UI outside ViewModel                            |



GlobalScope
************

import kotlinx.coroutines.*
fun main() {
    println("Main Function")
    GlobalScope.launch{
        suspendFunctionExample()
    }
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")  // This line is not printed because applicaiton is not waiting for coroutine to finish. Adding delay in main will solve this.
}

Output :- // 

Main Function
Process finished with exit code 0


runBlocking Scopec:- It will block the main thread until all the coroutines inside it complete their execution.
*****************
import kotlinx.coroutines.*

fun main() {
    println("Main Function")
    runBlocking {
        launch {
            suspendFunctionExample()
        }
    }
}

suspend fun suspendFunctionExample() {
    println("Suspend Function")
}

Output :-

Main Function
Suspend Function

Process finished with exit code 0

✅ CoroutineScope
******************
A general-purpose scope — you create it yourself, and you must cancel it manually to avoid memory leaks.
There is no automatic cancellation.


Why CoroutineScope is different?

Because it has no lifecycle awareness by default.

So if you don’t cancel it, the coroutine keeps running even if:
The user leaves the screen
The activity/fragment is destroyed
The ViewModel is gone
👉 That leads to memory leaks, crashes and unwanted background work.


import kotlinx.coroutines.*
// Dispatchers.Main is NOT available in a normal JVM console app , because it requires Android's Looper (UI thread)
private val scope =CoroutineScope(Dispatchers.Default)

fun main() {
    println("Main function Enter")
    val job=scope.launch {
        suspendFunctionExample()
        println("Inside Scope Function")
    }
    job.cancel()
    println("Main function Finished")
}

suspend fun suspendFunctionExample() {
    println("Suspend Function ${Thread.currentThread().name}")
}


ViewModel Scope
****************



lifecycleScope
***************
🏛 lifecycleScope (Recommended in Activity/Fragment)
Tied to Activity/Fragment lifecycle → Stopped automatically when destroyed.


package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch{
            println("Coroutine Tied to Activity/Fragment")
        }
    }
}


Note :- We cannot use lifecycleScope in a non-Android example. ❌

Here’s why:
lifecycleScope comes from:
import androidx.lifecycle.lifecycleScope


supervisorScope
****************


| API                     | Type                          | Can create a coroutine by itself? | Needs parent coroutine?                   |
| ----------------------- | ----------------------------- | --------------------------------- | ----------------------------------------- |
| `GlobalScope.launch {}` | Coroutine **scope** + builder | ✔ YES                             | ❌ No                                      |
| `supervisorScope {}`    | **Suspend function**          | ❌ NO                              | ✔ Yes (must run inside another coroutine) |


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.lang.RuntimeException

fun main()  {
    println("Main function Enter")
    runBlocking {
        supervisorScope {
            launch {
                failureFuction() //  Will throw exception ❌
            }
            launch {
                sucessFunction() // Should continue running ✔
            }
        }
    }
    println("Main function Ends")
}

 suspend fun sucessFunction(){
     repeat(5){ i->
         delay(100)
         println("SucessFunction $i")
     }
     println("End Sucess Function")
 }

suspend fun failureFuction(){
    delay(100)
    println("Failure function calling")
    throw RuntimeException("Boom Failure")
}

Output :-
Main function Enter
Failure function calling
Exception in thread "main" java.lang.RuntimeException: Boom Failure
	at CheckLogicKt.failureFuction(CheckLogic.kt:33)
	at CheckLogicKt$failureFuction$1.invokeSuspend(CheckLogic.kt)
	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
	at kotlinx.coroutines.DispatchedTaskKt.resume(DispatchedTask.kt:235)
	at kotlinx.coroutines.DispatchedTaskKt.dispatch(DispatchedTask.kt:168)
	at kotlinx.coroutines.CancellableContinuationImpl.dispatchResume(CancellableContinuationImpl.kt:474)
	at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl(CancellableContinuationImpl.kt:508)
	at kotlinx.coroutines.CancellableContinuationImpl.resumeImpl$default(CancellableContinuationImpl.kt:497)
	at kotlinx.coroutines.CancellableContinuationImpl.resumeUndispatched(CancellableContinuationImpl.kt:595)
	at kotlinx.coroutines.EventLoopImplBase$DelayedResumeTask.run(EventLoop.common.kt:493)
	at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:280)
	at kotlinx.coroutines.BlockingCoroutine.joinBlocking(Builders.kt:85)
	at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking(Builders.kt:59)
	at kotlinx.coroutines.BuildersKt.runBlocking(Unknown Source)
	at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(Builders.kt:38)
	at kotlinx.coroutines.BuildersKt.runBlocking$default(Unknown Source)
	at CheckLogicKt.main(CheckLogic.kt:9)
	at CheckLogicKt.main(CheckLogic.kt)
	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [StandaloneCoroutine{Cancelling}@3b22cdd0, BlockingEventLoop@1e81f4dc]
SucessFunction 0
SucessFunction 1
SucessFunction 2
SucessFunction 3
SucessFunction 4
End Sucess Function
Main function Ends

Use this for Non-Android code 
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"



MainScope
*********
class ExampleActivity : AppCompatActivity() {

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scope.launch {
            updateUI()  // safe because Dispatchers.Main
        }
    }

    override fun onDestroy() {
        scope.cancel() // very important!
    }
}
----------------------------------------------------------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------