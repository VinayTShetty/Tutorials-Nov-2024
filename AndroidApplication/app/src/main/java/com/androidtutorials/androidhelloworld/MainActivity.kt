package com.androidtutorials.androidhelloworld

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.Toast

/**
 * MainActivity
 *
 * Acts as a client that binds to MyBoundService
 * and communicates with it using a Binder.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        /**
         * TAG used for logging Activity events.
         */
        const val TAG = "MainActivity"
    }

    /**
     * Reference to the bound Service.
     *
     * This reference is obtained from the Binder
     * and is valid only while the Service is bound.
     */
    private var service: MyBoundService? = null

    /**
     * Indicates whether the Service is currently bound.
     */
    private var isBound = false

    /**
     * ServiceConnection
     *
     * Interface used to monitor the connection
     * between the Activity and the Service.
     */
    private val connection = object : ServiceConnection {

        /**
         * Called when the Service is successfully connected.
         *
         * @param name ComponentName of the Service
         * @param binder IBinder returned from Service.onBind()
         */
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(TAG, "Service connected")

            // Cast IBinder to our custom Binder
            val myBinder = binder as MyBoundService.MyBinder

            // Get the Service instance from the Binder
            service = myBinder.getService()

            isBound = true
        }

        /**
         * Called when the Service is unexpectedly disconnected.
         *
         * This usually happens if the Service crashes.
         */
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "Service disconnected")
            service = null
            isBound = false
        }
    }

    /**
     * Called when the Activity is created.
     *
     * Initializes UI and sets up button listeners.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MyBoundService::class.java)

        /**
         * Bind button
         *
         * Requests the system to bind the Activity
         * to the Service.
         */
        findViewById<Button>(R.id.btnBind).setOnClickListener {
            Log.d(TAG, "Bind button clicked")
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        /**
         * Unbind button
         *
         * Disconnects the Activity from the Service.
         */
        findViewById<Button>(R.id.btnUnbind).setOnClickListener {
            Log.d(TAG, "Unbind button clicked")
            if (isBound) {
                unbindService(connection)
                isBound = false
                service = null
            }
        }

        /**
         * Call button
         *
         * Calls a public method of the bound Service
         * and displays the result.
         */
        findViewById<Button>(R.id.btnCall).setOnClickListener {
            Log.d(TAG, "Call button clicked")
            if (isBound) {
                Toast.makeText(
                    this,
                    service?.getMessage(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
