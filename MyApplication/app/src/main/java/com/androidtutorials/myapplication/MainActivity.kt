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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

const val TAG = "📸 CAMERA_PERMISSION_LOG"

/**
 * Represents the complete set of possible camera permission states.
 *
 * - **DENIED** – User has not granted the permission (initial state or after cancel)
 * - **GRANTED** – User accepted the permission
 * - **RATIONALE** – User denied once, but did NOT select “Don't ask again”
 * - **PERMANENTLY_DENIED** – User denied AND selected “Don't Ask Again”, or disabled permission in settings
 */
enum class CameraPermissionState {
    DENIED,
    GRANTED,
    RATIONALE,
    PERMANENTLY_DENIED
}

/**
 * Main activity that initializes the app UI.
 *
 * Loads the `CameraPermissionScreen()` composable which handles
 * the entire permission flow for the CAMERA permission.
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
                    CameraPermissionScreen()
                }
            }
        }
    }
}

/**
 * Composable that manages the full flow of requesting camera permission.
 *
 * It handles:
 * - Launching permission request using `rememberLauncherForActivityResult`
 * - Interpreting system responses (granted / denied / permanently denied)
 * - Displaying rationale or settings dialogs based on permission result
 *
 * This composable contains the full decision tree:
 *
 * When permission is denied, the function checks
 * `ActivityCompat.shouldShowRequestPermissionRationale()`.
 *
 * **Behavior of shouldShowRequestPermissionRationale():**
 *
 * 1. Returns **true** when:
 *    - User denied permission normally (once)
 *    - Android recommends showing rationale dialog
 *
 * 2. Returns **false** when:
 *    - This is the first time permission is requested
 *    - User denied and selected “Don’t Ask Again”
 *    - Permission is disabled in app settings
 *
 * Because of this, the logic must be checked **after** the user denies.
 */
@Composable
fun CameraPermissionScreen() {

    val context = LocalContext.current
    val activity = context as? Activity
    val permission = Manifest.permission.CAMERA

    var permissionState by remember { mutableStateOf(CameraPermissionState.DENIED) }

    /**
     * Launcher that displays Android's system dialog for camera permission.
     *
     * The callback receives:
     * - `true` → permission granted
     * - `false` → permission denied
     *
     * After denial, the function determines whether the user denied temporarily
     * or permanently (i.e., used “Don’t Ask Again”).
     */
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->

        Log.d(TAG, "🎯 Permission callback fired. granted = $granted")

        if (granted) {
            Log.d(TAG, "✔ Permission GRANTED")
            permissionState = CameraPermissionState.GRANTED

        } else {
            Log.d(TAG, "❌ Permission DENIED")

            /**
             * Interprets how the user denied the permission.
             *
             * shouldShowRequestPermissionRationale returns:
             *
             * - **true** → The user denied once but did NOT press “Don't Ask Again”
             * - **false** → Either the first request OR permanently denied
             *
             * Since we are inside a denial callback, a `false` value here means
             * **permanent denial**.
             */
            val shouldShow = activity?.let {
                ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
            } ?: false

            Log.d(TAG, "ℹ shouldShowRequestPermissionRationale = $shouldShow")

            permissionState =
                if (shouldShow) CameraPermissionState.RATIONALE
                else CameraPermissionState.PERMANENTLY_DENIED
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        when (permissionState) {

            /**
             * Initial/default state where user can request permission.
             */
            CameraPermissionState.DENIED -> {
                Button(
                    onClick = {
                        Log.d(TAG, "📥 Requesting CAMERA permission…")
                        launcher.launch(permission)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Request Camera Permission")
                }
            }

            /**
             * Displayed only when the device gives full access to the camera.
             */
            CameraPermissionState.GRANTED -> {
                Log.d(TAG, "📸 PermissionState.GRANTED UI displayed")

                Text(
                    "✔ 📸 Camera Permission Granted!",
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            /**
             * Shown when permission was denied but can still be requested again.
             */
            CameraPermissionState.RATIONALE -> {

                Log.d(TAG, "🟨 RATIONALE dialog displayed")

                RationaleDialog(
                    onRetry = {
                        Log.d(TAG, "🔁 Retrying from rationale dialog")
                        launcher.launch(permission)
                    },
                    onCancel = {
                        Log.d(TAG, "❌ Rationale cancelled")
                        permissionState = CameraPermissionState.DENIED
                    }
                )
            }

            /**
             * Indicates the user selected “Don’t Ask Again”.
             *
             * Only solution is sending the user to system settings.
             */
            CameraPermissionState.PERMANENTLY_DENIED -> {

                Log.d(TAG, "🔒 Permission permanently denied")

                SettingDialog(
                    onOpenSettings = {
                        Log.d(TAG, "⚙ Opening settings")
                        openAppSettings(context)
                    },
                    onCancel = {
                        Log.d(TAG, "❌ Settings dialog cancelled")
                        permissionState = CameraPermissionState.DENIED
                    }
                )
            }
        }
    }
}

/**
 * Opens the system settings screen for the application so the
 * user can manually grant camera permissions after permanent denial.
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
 * Dialog shown when permission is temporarily denied.
 *
 * Provides:
 * - A retry button to re-request permissions
 * - A cancel button to dismiss the explanation
 */
@Composable
fun RationaleDialog(
    onRetry: () -> Unit,
    onCancel: () -> Unit
) {

    Log.d(TAG, "🟡 Showing RationaleDialog()")

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Permission Needed") },
        text = { Text("ℹ The app needs camera permission to continue.") },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text("🔁 Try Again")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("❌ Cancel")
            }
        }
    )
}

/**
 * Dialog displayed when the user has permanently denied permission.
 *
 * This is triggered when:
 * - User tapped “Don’t Ask Again”
 * - Permission disabled manually from settings
 *
 * Provides:
 * - A button to navigate to the app settings page
 * - A cancel button to return to the UI
 */
@Composable
fun SettingDialog(
    onOpenSettings: () -> Unit,
    onCancel: () -> Unit
) {

    Log.d(TAG, "🔴 Showing SettingDialog()")

    AlertDialog(
        onDismissRequest = {},
        title = { Text("Permission Permanently Denied") },
        text = {
            Text(
                "🔒 You have permanently denied CAMERA permission.\n\n" +
                        "Please enable it manually in app settings."
            )
        },
        confirmButton = {
            TextButton(onClick = onOpenSettings) {
                Text("⚙ Open Settings")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("❌ Cancel")
            }
        }
    )
}
