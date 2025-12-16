package com.androidtutorials.androidhelloworld

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * MyBoundService
 *
 * A Bound Service allows components (like Activities)
 * to bind to it and interact using method calls.
 *
 * This is a LOCAL bound service, meaning:
 * - Client and Service run in the SAME process
 * - No IPC (AIDL) is required
 */
class MyBoundService : Service() {

    companion object {
        /**
         * TAG used for logging service lifecycle events.
         */
        const val TAG = "MyBoundService"
    }

    /**
     * Binder instance returned to clients when they bind.
     *
     * This Binder provides a way for the Activity
     * to obtain the Service instance.
     */
    private val binder = MyBinder()

    /**
     * MyBinder
     *
     * Custom Binder class that exposes the Service instance.
     *
     * Why this class is needed:
     * - Android requires an IBinder object for binding
     * - This Binder acts as a bridge between Activity and Service
     *
     * Declared as `inner` so it can access the outer
     * MyBoundService instance.
     */
    inner class MyBinder : Binder() {

        /**
         * Returns the current Service instance.
         *
         * This allows the Activity to directly call
         * public methods of the Service.
         */
        fun getService(): MyBoundService = this@MyBoundService
    }

    /**
     * Called once when the Service is first created.
     *
     * This method is used to perform one-time setup.
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")
    }

    /**
     * Called when a client binds to the Service using bindService().
     *
     * @param intent The Intent used to bind to the Service
     * @return IBinder object used by the client to communicate
     *         with the Service
     *
     * IMPORTANT:
     * - This method MUST return an IBinder
     * - The returned Binder is passed to the client
     *   via ServiceConnection.onServiceConnected()
     */
    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "Service bound")
        return binder
    }

    /**
     * Called when all clients have unbound from the Service.
     *
     * @param intent The Intent used for unbinding
     * @return true if rebind should be handled
     */
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "Service unbound")
        return super.onUnbind(intent)
    }

    /**
     * Called when the Service is destroyed.
     *
     * This happens when no clients are bound
     * and the Service is no longer needed.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service destroyed")
    }

    /**
     * Public method exposed to bound clients.
     *
     * The Activity can call this method after
     * a successful service binding.
     *
     * @return A simple message from the Service
     */
    fun getMessage(): String {
        Log.d(TAG, "getMessage() called from Activity")
        return "Hello From Bound Service"
    }
}
