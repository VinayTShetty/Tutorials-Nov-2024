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

class MainActivity : AppCompatActivity() {

    private val TAG = "MULTI_PERMISSION"

    /**
     * All permissions app needs.
     * Order does NOT matter.
     */
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private val REQUEST_CODE_PERMISSIONS = 2001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkMultiplePermissions()
    }

    /**
     * --------------------------------------------------------------------
     * STEP 1 — CHECK which permissions are denied
     *
     * TRUE: permission already granted
     * FALSE: permission not granted → add to denied list
     * --------------------------------------------------------------------
     */
    private fun checkMultiplePermissions() {

        // Filter out which permissions are still NOT granted
        val deniedPermissions = REQUIRED_PERMISSIONS.filter { permission ->
            ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED
        }

        // If empty → all permissions granted already
        if (deniedPermissions.isEmpty()) {
            Log.d(TAG, "✅ All permissions already granted")
            return
        }

        // FIRST TIME or user denied earlier → normal request
        requestPermissionsNormally(deniedPermissions)
    }

    /**
     * --------------------------------------------------------------------
     * STEP 2 — Request permissions normally
     * This happens:
     *  1️⃣ First app launch
     *  2️⃣ User denied before (not don't ask again)
     *
     * DO NOT enter settings from here.
     * --------------------------------------------------------------------
     */
    private fun requestPermissionsNormally(permissions: List<String>) {

        Log.d(TAG, "📌 Requesting permissions: $permissions")

        ActivityCompat.requestPermissions(
            this,
            permissions.toTypedArray(),
            REQUEST_CODE_PERMISSIONS
        )
    }

    /**
     * --------------------------------------------------------------------
     * STEP 3 — RESULT OF ALL PERMISSIONS
     * This function runs ONLY after user takes action.
     *
     * IMPORTANT:
     * shouldShowRequestPermissionRationale() behavior:
     *
     * RETURNS TRUE  → User denied normally (NO "Don't Ask Again")
     * RETURNS FALSE → Two possibilities:
     *                 (A) First time → FALSE (normal)
     *                 (B) User selected "Don't Ask Again" → FALSE (IMPORTANT)
     *
     * But FIRST TIME never enters this function,
     * so here FALSE ALWAYS means "Don't Ask Again" selected.
     * --------------------------------------------------------------------
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != REQUEST_CODE_PERMISSIONS) return

        val deniedList = mutableListOf<String>()
        val dontAskAgainList = mutableListOf<String>()

        // Loop each permission one by one
        permissions.forEachIndexed { index, permission ->

            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {

                // Permission granted
                Log.d(TAG, "✔ GRANTED: $permission")

            } else {

                // Permission denied
                val rationale =
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

                Log.d(TAG, "❌ DENIED: $permission | showRationale=$rationale")

                /**
                 * TRUE  → User denied normally ("Allow/Deny")
                 * FALSE → User clicked "Don't Ask Again" (because this function is NEVER called first time)
                 */
                if (!rationale) {
                    dontAskAgainList.add(permission)
                } else {
                    deniedList.add(permission)
                }
            }
        }
        /**
          Note :- For each individual Permission its not showing and checking on
          Setting because we are not handling that inside this forEachIndexed Loop.

         Also its not recommended to do.
         */
        // CASE 1 — User clicked "Don't Ask Again"
        if (dontAskAgainList.isNotEmpty()) {
            Log.d(TAG, "🚫 DON'T ASK AGAIN → $dontAskAgainList")
            showSettingsDialog()
            return
        }

        // CASE 2 — User denied normally
        if (deniedList.isNotEmpty()) {
            Log.d(TAG, "⚠️ Denied Normally → $deniedList")
            showRationaleDialog(deniedList)
            return
        }

        // CASE 3 — All granted
        Log.d(TAG, "🎉 ALL permissions granted!")
    }

    /**
     * --------------------------------------------------------------------
     * STEP 4 — Show rationale when user DENIED normally
     * This dialog RE-TRIGGERS permission request.
     * --------------------------------------------------------------------
     */
    private fun showRationaleDialog(deniedPermissions: List<String>) {

        AlertDialog.Builder(this)
            .setTitle("Permissions Needed")
            .setMessage("These permissions are required for the app to work properly.")
            .setPositiveButton("Retry") { _, _ ->

                Log.d(TAG, "🔁 Retrying permissions: $deniedPermissions")

                ActivityCompat.requestPermissions(
                    this,
                    deniedPermissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS
                )
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    /**
     * --------------------------------------------------------------------
     * STEP 5 — "Don't Ask Again" → SETTINGS SCREEN
     *
     * ONLY shown when:
     * shouldShowRequestPermissionRationale() == FALSE
     * AFTER user DENIED a permission earlier.
     * --------------------------------------------------------------------
     */
    private fun showSettingsDialog() {

        AlertDialog.Builder(this)
            .setTitle("Permission Required")
            .setMessage(
                "Some permissions are permanently denied.\n" +
                        "Please enable them from Settings."
            )
            .setPositiveButton("Open Settings") { _, _ ->
                openAppSettings()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}

/**

==============================================================
📘 PERMISSION FLOW DOCUMENTATION — SUPER CLEAR
==============================================================

1️⃣ FIRST TIME (NO dialog before)
--------------------------------
✓ shouldShowRequestPermissionRationale() = FALSE
✓ BUT onRequestPermissionsResult() is NOT called here
✓ So “FALSE” here is NORMAL.

2️⃣ USER DENIES
--------------------------------
✓ shouldShowRequestPermissionRationale() = TRUE
✓ Means: user denied, but didn’t choose “Don’t Ask Again”.

3️⃣ USER SELECTS "DON’T ASK AGAIN"
--------------------------------
✓ shouldShowRequestPermissionRationale() = FALSE
✓ AND onRequestPermissionsResult() RUNS
✓ → MUST navigate to SETTINGS

4️⃣ MULTIPLE PERMISSIONS BEHAVIOR
--------------------------------
✓ Each permission is checked individually.
✓ Even if 1 permission has “Don’t Ask Again”
→ You MUST show settings dialog.

5️⃣ WHY DOES IT SOMETIMES SKIP TO SETTINGS?
-------------------------------------------
Because ONE permission out of the list was denied earlier
WITH "Don't Ask Again" → even if others are first time.

==============================================================
This code handles:
✓ First time request
✓ Normal denial
✓ Don’t Ask Again denial
✓ Multiple permissions at once
==============================================================

 */
