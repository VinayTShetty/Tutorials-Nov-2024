package com.androidtutorials.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

private const val TAG = "DisposableEffectDemo"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisposableEffectKeyDemo()
                }
            }
        }
    }
}

@Composable
fun DisposableEffectKeyDemo() {

    var networkType by remember { mutableStateOf("WIFI") }
    var dummyCounter by remember { mutableStateOf(0) }

    val listener = remember { FakeConnectionListner() }

    // 🔥 This block reacts ONLY when networkType changes
    DisposableEffect(networkType) {
        Log.d(TAG, "🟢 SETUP: Listener for $networkType")
        listener.start_fakelistner()

        onDispose {
            Log.d(TAG, "❌ CLEANUP: Listener for $networkType")
            listener.stop_fakelistner()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Network: $networkType", fontSize = 22.sp)
        Text("Dummy State: $dummyCounter", fontSize = 18.sp)

        Spacer(Modifier.height(20.dp))

        // FIXED: This must update the state
        Button(onClick = {
            networkType = if (networkType == "WIFI") "MOBILEDATA" else "WIFI"
            Log.d(TAG, "🔄 Network Type Changed to: $networkType")
        }) {
            Text("Change Network Type (Trigger DisposableEffect)")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            dummyCounter++
            Log.d(TAG, "🔁 Dummy Counter Changed: $dummyCounter (NO DisposableEffect Trigger)")
        }) {
            Text("Recompose WITHOUT Key Change")
        }
    }
}

class FakeConnectionListner {
    fun start_fakelistner() {
        Log.d(TAG, "📡 Listener Started")
    }

    fun stop_fakelistner() {
        Log.d(TAG, "❌ Listener Stopped")
    }
}
