package com.androidtutorials.androidhelloworld

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // TextView to display contacts
    private lateinit var tvContacts: TextView

    // Request code for permission callback
    private val CONTACT_PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Attach UI layout
        setContentView(R.layout.activity_main)

        // Get TextView reference
        tvContacts = findViewById(R.id.tvContacts)

        // Step 1: Check & request permission
        checkRequestPermissions()
    }

    /**
     * Checks if READ_CONTACTS permission is already granted
     * If yes → read contacts
     * If no → request permission
     */
    private fun checkRequestPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission already granted
            readContacts()
        } else {
            // Ask user for permission
            requestPermission()
        }
    }

    /**
     * Triggers runtime permission dialog
     */
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_CONTACTS),
            CONTACT_PERMISSION_CODE
        )
    }

    /**
     * Callback after user allows or denies permission
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Check correct request code + permission granted
        if (requestCode == CONTACT_PERMISSION_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission granted → read contacts
            readContacts()
        }
    }

    /**
     * Reads contacts using Android Contacts ContentProvider
     */
    private fun readContacts() {

        val sb = StringBuilder()

        // Query the Contacts ContentProvider
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // Content URI
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, // Column 0
                ContactsContract.CommonDataKinds.Phone.NUMBER        // Column 1
            ),
            null, // selection
            null, // selection args
            null  // sort order
        )

        // Read data from cursor
        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(0)
                val number = it.getString(1)

                sb.append("$name : $number\n\n")
            }
        }

        // Display contacts on screen
        tvContacts.text = sb.toString()
    }
}
