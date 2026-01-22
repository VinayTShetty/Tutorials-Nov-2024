package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val TAG = "WITH_CONTEXT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {

            // ============================================================
            // CASE 1 : Start in IO → withContext(IO)
            // (Same dispatcher, no real switch)
            // ============================================================

            Log.d(
                TAG,
                "CASE 1 | BEFORE withContext(IO) -> " +
                        "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
            )

            withContext(Dispatchers.IO) {
                Log.d(
                    TAG,
                    "CASE 1 | INSIDE withContext(IO) -> " +
                            "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )
            }

            // ============================================================
            // CASE 2 : IO → Default (Dispatcher SWITCH)
            // ============================================================

            Log.d(
                TAG,
                "CASE 2 | BEFORE withContext(Default) -> " +
                        "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
            )

            withContext(Dispatchers.Default) {
                Log.d(
                    TAG,
                    "CASE 2 | INSIDE withContext(Default) -> " +
                            "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )
            }

            // ============================================================
            // CASE 3 : IO → Default → Default (Nested, SAME coroutine)
            // ============================================================

            withContext(Dispatchers.Default) {

                Log.d(
                    TAG,
                    "CASE 3 | INSIDE Default (Level 1) -> " +
                            "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )

                withContext(Dispatchers.Default) {
                    Log.d(
                        TAG,
                        "CASE 3 | INSIDE Default (Level 2) -> " +
                                "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                    )
                }
            }

            // ============================================================
            // CASE 4 : IO → Default → Main (UI switch)
            // ============================================================

            withContext(Dispatchers.Default) {

                Log.d(
                    TAG,
                    "CASE 4 | INSIDE Default -> " +
                            "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                )

                withContext(Dispatchers.Main) {
                    Log.d(
                        TAG,
                        "CASE 4 | INSIDE Main -> " +
                                "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
                    )
                }
            }

            // ============================================================
            // BACK TO IO (Automatic)
            // ============================================================

            Log.d(
                TAG,
                "AFTER all withContext -> " +
                        "Thread = ${Thread.currentThread().name} | id = ${Thread.currentThread().id}"
            )
        }
    }
}
