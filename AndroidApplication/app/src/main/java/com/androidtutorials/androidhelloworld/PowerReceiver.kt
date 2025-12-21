package com.androidtutorials.androidhelloworld

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class WifiReceiver : BroadcastReceiver() {

    private val TAG = "BroadcastReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == ConnectivityManager.CONNECTIVITY_ACTION) {

            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)

            val isWifiConnected =
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true

            if (isWifiConnected) {
                Log.d(TAG, "WIFI CONNECTED")
            } else {
                Log.d(TAG, "WIFI DISCONNECTED")
            }
        }
    }
}
