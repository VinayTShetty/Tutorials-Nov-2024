package com.androidtutorials.myapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

// 🔍 Global TAG
const val TAG = "📦 MULTI_PERMISSION_LOG"

/**
 * Represents all possible states of MULTIPLE permissions.
 *
 * - NOT_GRANTED → Permissions not yet requested OR cancelled.
 * - PARTIALLY_GRANTED → Some permissions granted, some denied (temporary).
 * - RATIONALE_NEEDED → One or more permissions denied but user did NOT press “Don't Ask Again”.
 * - PERMANENTLY_DENIED → One or more permissions permanently denied.
 * - ALL_GRANTED → All permissions granted successfully.
 */
enum class MultiPermissionState {
    NOT_GRANTED,
    PARTIALLY_GRANTED,
    RATIONALE_NEEDED,
    PERMANENTLY_DENIED,
    ALL_GRANTED
}

/**
 * MainActivity that loads the MultiPermissionScreen.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "🟦 MainActivity.onCreate()")

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MultiPermissionScreen()
                }
            }
        }
    }
}

/**
 * MultiPermissionScreen handles requesting and evaluating multiple permissions.
 *
 * In this sample we request:
 *  - CAMERA
 *  - ACCESS_FINE_LOCATION
 *
 * HOW MULTIPLE PERMISSION CALLBACK WORKS:
 * ---------------------------------------
 * When using RequestMultiplePermissions(), the callback returns:
 *
 * Map<String, Boolean>
 *
 * Example:
 * { "android.permission.CAMERA" = true,
 *   "android.permission.ACCESS_FINE_LOCATION" = false }
 *
 * After receiving this map, we must:
 *
 * 1. Check which permissions were granted
 * 2. For permissions denied, check:
 *
 *   shouldShowRequestPermissionRationale(permission)
 *
 *   - true  → Temporary denial → show rationale
 *   - false → Permanently denied OR first request
 *
 * In multi-permission scenarios:
 *
 * - If ANY permission is permanently denied → show settings dialog
 * - If ANY permission needs rationale → show rationale dialog
 * - If ALL permissions granted → success!
 */
@Composable
fun MultiPermissionScreen() {

    val context = LocalContext.current
    val activity = context as? Activity

    // List of permissions we want
    val permissions = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS
    )

    // Holds state of permission flow
    var permissionState by remember { mutableStateOf(MultiPermissionState.NOT_GRANTED) }
    LaunchedEffect(permissionState) {
        Log.d(TAG, "🔥 UI is rendering state = $permissionState")
    }

    /**
     * Launcher that requests ALL permissions at once.
     *
     * The callback returns a map showing which permissions were granted.
     */
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result: Map<String, Boolean> ->

        Log.d(TAG, "🎯 Multi-permission callback fired → $result")

        val deniedPermissions = result.filterValues { !it }.keys
        val grantedPermissions = result.filterValues { it }.keys

        Log.d(TAG, "✔ Granted = $grantedPermissions")
        Log.d(TAG, "❌ Denied = $deniedPermissions")

        // If NO permissions were denied → all granted
        if (deniedPermissions.isEmpty()) {
            permissionState = MultiPermissionState.ALL_GRANTED
            return@rememberLauncherForActivityResult
        }

        // Check rationale meaning for each denied permission
        var needsRationale = false
        var permanentlyDenied = false

        deniedPermissions.forEach { perm ->
            val showRationale =
                activity?.let {
                    ActivityCompat.shouldShowRequestPermissionRationale(it, perm)
                } ?: false

            Log.d(TAG, "ℹ shouldShowRequestPermissionRationale($perm) = $showRationale")

            if (showRationale) {
                needsRationale = true
            } else {
                permanentlyDenied = true
            }
        }

        permissionState = when {
            permanentlyDenied -> MultiPermissionState.PERMANENTLY_DENIED
            needsRationale -> MultiPermissionState.RATIONALE_NEEDED
            else -> MultiPermissionState.PARTIALLY_GRANTED
        }
    }

    // ========================================================
    // UI
    // ========================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        when (permissionState) {

            MultiPermissionState.NOT_GRANTED -> {
                Button(
                    onClick = {
                        Log.d(TAG, "📥 Requesting MULTIPLE permissions...")
                        launcher.launch(permissions.toTypedArray())
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Request Camera + Location Permissions")
                }
            }

            MultiPermissionState.ALL_GRANTED -> {
                Log.d(TAG, "🏆 ALL permissions granted!")

                Text(
                    "🎉 All Permissions Granted!",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            MultiPermissionState.PARTIALLY_GRANTED -> {
                Log.d(TAG, "⚠ Some permissions granted, some denied.")

                Text("Some permissions denied. Try again.")
            }

            MultiPermissionState.RATIONALE_NEEDED -> {
                Log.d(TAG, "🟨 Showing Multi-Rationale dialog")

                MultiRationaleDialog(
                    onRetry = {
                        launcher.launch(permissions.toTypedArray())
                    },
                    onCancel = {
                        permissionState = MultiPermissionState.NOT_GRANTED
                    }
                )
            }

            MultiPermissionState.PERMANENTLY_DENIED -> {
                Log.d(TAG, "🔴 One or more permissions permanently denied")

                MultiSettingsDialog(
                    onOpenSettings = { openAppSettings(context) },
                    onCancel = { permissionState = MultiPermissionState.NOT_GRANTED }
                )
            }
        }
    }
}

/**
 * Opens the system settings page so the user can manually grant permissions.
 */
fun openAppSettings(context: Context) {

    Log.d(TAG, "➡ Opening app settings")

    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )

    context.startActivity(intent)
}

/**
 * Dialog shown when one or more permissions require rationale.
 */
@Composable
fun MultiRationaleDialog(
    onRetry: () -> Unit,
    onCancel: () -> Unit
) {
    Log.d(TAG, "🟡 Showing MultiRationaleDialog()")

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Permissions Needed") },
        text = {
            Text(
                "The app needs Camera and Location permissions.\n" +
                        "Please allow them to continue."
            )
        },
        confirmButton = {
            TextButton(onClick = onRetry) { Text("🔁 Try Again") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("❌ Cancel") }
        }
    )
}

/**
 * Dialog shown when user permanently denies ANY permission.
 */
@Composable
fun MultiSettingsDialog(
    onOpenSettings: () -> Unit,
    onCancel: () -> Unit
) {
    Log.d(TAG, "🔴 Showing MultiSettingsDialog()")

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Permissions Permanently Denied") },
        text = {
            Text(
                "One or more permissions were permanently denied.\n" +
                        "Enable them in settings to continue."
            )
        },
        confirmButton = {
            TextButton(onClick = onOpenSettings) { Text("⚙ Open Settings") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("❌ Cancel") }
        }
    )
}


/**
 Summary of the Table

 | Condition                                            | Final State          |
 | ---------------------------------------------------- | -------------------- |
 | ANY perm is permanently denied                       | `PERMANENTLY_DENIED` |
 | No permanent denial, but ANY denied with rationale   | `RATIONALE_NEEDED`   |
 | No permanent denial and no rationale but some denied | `PARTIALLY_GRANTED`  |
 | None denied                                          | `ALL_GRANTED`        |


 */