package com.androidtutorials.androidhelloworld

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION = Manifest.permission.CAMERA
    private val REQUEST_CODE_CAMERA = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🚀 Step 1: Start permission check immediately
        checkCameraPermission()
    }

    /**
     * 🔍 This function decides:
     *   - WHETHER permission is granted,
     *   - WHETHER to show rationale,
     *   - OR whether to request permission for the first time.
     */
    private fun checkCameraPermission() {

        // ✔️ Check if permission is already granted
        val granted =
            ContextCompat.checkSelfPermission(this, CAMERA_PERMISSION) ==
                    PackageManager.PERMISSION_GRANTED

        // 🎯 granted = TRUE when:
        //    - User previously allowed permission
        //
        // 🎯 granted = FALSE when:
        //    - First time install → first-time request
        //    - User denied once
        //    - User denied + clicked Don't Ask Again

        if (granted) {

            Log.d("PERMISSION", "✅ Camera permission ALREADY granted")

        } else {
            // ❌ Permission is NOT granted

            val showRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA_PERMISSION)

            // 🎯 shouldShowRequestPermissionRationale() cases:
            //
            //   👉 TRUE  = User denied previously WITHOUT Don't Ask Again
            //       - User saw permission dialog before
            //       - User tapped ❌ Deny
            //       - BUT did NOT check “Don’t ask again”
            //
            //   👉 FALSE = Either:
            //         1️⃣ First-time asking permission
            //         2️⃣ User denied + checked “❌ Don’t Ask Again”
            //         3️⃣ Already granted (handled above)

            if (showRationale) {

                Log.d("PERMISSION", "ℹ️ Showing rationale dialog to user")

                // User denied before, so give them explanation
                showRationaleDialog()

            } else {

                Log.d(
                    "PERMISSION",
                    "📌 Requesting permission (first-time OR denied with DON'T ASK AGAIN)"
                )

                // 🔔 Shows Android system permission popup
                requestCameraPermission()
            }
        }
    }

    /**
     * 📘 Shown ONLY when:
     *   - User denied permission once
     *   - User did NOT click “Don't ask again”
     */
    private fun showRationaleDialog() {

        AlertDialog.Builder(this)
            .setTitle("Camera Permission Needed")
            .setMessage("We need camera access to take photos.")
            .setPositiveButton("OK") { _, _ ->

                Log.d("PERMISSION", "👍 User accepted rationale → requesting again")

                // User understands → Ask permission again
                requestCameraPermission()
            }
            .setNegativeButton("Cancel") { dialog, _ ->

                Log.d("PERMISSION", "👎 User cancelled rationale")
                dialog.dismiss()
            }
            .show()
    }

    /**
     * 📝 Requests Android’s native permission popup.
     */
    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(CAMERA_PERMISSION),
            REQUEST_CODE_CAMERA
        )
        Log.d("PERMISSION", "requestCameraPermission:  Requesting Permission")
    }

    /**
     * 🎯 Receives result from the system:
     *      ✔ Allow
     *      ❌ Deny
     *      ❌ Deny + Don’t Ask Again
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_CAMERA) {

            // --------------------------------------------
            // 🔍 Case 1 → User clicked ALLOW
            // --------------------------------------------
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {

                Log.d("PERMISSION", "🎉 Camera permission GRANTED by user")
                return
            }

            // --------------------------------------------
            // 🔍 Case 2 → User clicked DENY
            // --------------------------------------------
            Log.d("PERMISSION", "❌ Camera permission DENIED by user")

            // Check if "Don't Ask Again" was chosen
            val showRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA_PERMISSION)

            // 🎯 showRationale returns:
            //
            // TRUE  = User denied normally (NO Don't Ask Again)
            // FALSE = User checked DON'T ASK AGAIN ❌

            if (!showRationale) {

                // 🚨 shouldShowRequestPermissionRationale == FALSE AND permission is still denied
                // Means:
                //      👉 User DENIED AND clicked “Don’t ask again”
                Log.d("PERMISSION", "🚫 User selected DON'T ASK AGAIN")

                // Now we must guide user to settings
                showSettingsDialog()

            } else {

                // User only denied without Don't Ask Again
                Log.d("PERMISSION", "⚠️ User denied WITHOUT don't ask again")
            }
        }
    }

    /**
     * ⚠️ This dialog ONLY appears when:
     *      ❌ User denied AND
     *      ❌ checked “Don't ask again”
     */
    private fun showSettingsDialog() {

        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage(
                "Camera permission is permanently denied. " +
                        "Please go to Settings and enable the permission manually."
            )
            .setPositiveButton("Open Settings") { _, _ ->

                // Take user to:
                // Settings → Apps → <YourApp> → Permissions
                openAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    /**
     * ⚙️ Opens the App Settings page
     */
    private fun openAppSettings() {

        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
