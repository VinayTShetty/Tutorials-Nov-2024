package com.androidtutorials.myapplication

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * SimpleLifecycleObserver
 *
 * This class is a Lifecycle-Aware Component.
 * It listens to lifecycle events of a screen (Activity / Fragment / Compose screen)
 * WITHOUT being part of the UI itself.
 *
 * ➜ No Activity code
 * ➜ No Compose code
 * ➜ Only lifecycle-related logic
 *
 * This helps in:
 * - Separation of concerns
 * - Reusability
 * - Avoiding memory leaks
 */
class SimpleLifecycleObserver : DefaultLifecycleObserver {

    /**
     * onStart()
     *
     * Called when the screen becomes VISIBLE to the user.
     *
     * Examples of real usage:
     * - Start camera
     * - Start sensor
     * - Start analytics tracking
     * - Resume media playback
     *
     * 'owner' represents the LifecycleOwner (Activity / Fragment / Compose screen)
     */
    override fun onStart(owner: LifecycleOwner) {
        Log.d(
            LogConstants.TAG_LIFECYCLE,
            "onStart: Screen Started (Visible to user)"
        )
    }

    /**
     * onStop()
     *
     * Called when the screen is NO LONGER visible.
     *
     * Examples of real usage:
     * - Stop camera
     * - Unregister sensor
     * - Pause media playback
     * - Stop analytics tracking
     *
     * This ensures resources are released properly.
     */
    override fun onStop(owner: LifecycleOwner) {
        Log.d(
            LogConstants.TAG_LIFECYCLE,
            "onStop: Screen Stopped (Not visible)"
        )
    }
}
