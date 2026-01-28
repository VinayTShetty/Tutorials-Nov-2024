package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            sendDataUI()
        }


    }

    private fun sendDataToFireBase() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Users")

        val user = mapOf(
            "name" to "Vinay TS",
            "age" to 28,
            "City" to "Bangalore"
        )

        ref.push().setValue(user)
    }

    @Composable
    fun sendDataUI() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {sendDataToFireBase()}) {
                Text("Send Data")
            }
        }
    }

}
