package com.androidtutorials.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomNumberApp()
        }
        workManager= WorkManager.getInstance(applicationContext)
    }

    @Composable
    fun RandomNumberApp() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { startPeriodicWork() }) {
                Text("Start Generating Numbers")
            }
        }
    }

    private fun startPeriodicWork() {
        // Create a periodic work request (minimum interval of 15 minutes)
        val periodicWorkRequest = PeriodicWorkRequestBuilder<RandomNumberWorker>(15, TimeUnit.MINUTES)
            .build()
        // Enqueue the periodic work with a unique tag
        workManager.enqueueUniquePeriodicWork(
            "RandomWorkTag",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
}
