package com.androidtutorials.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.androidtutorials.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeCycleAwareScreen()
        }
    }

    /**
     * LifeCycleAwareScreen
     *
     * This is a Jetpack Compose screen.
     *
     * IMPORTANT:
     * - Composables do NOT have onCreate / onStart / onResume
     * - They can recompose multiple times
     * - So lifecycle logic CANNOT be written directly here
     *
     * Solution:
     * - Use LocalLifecycleOwner to access lifecycle
     * - Use DisposableEffect to safely attach and detach observers
     */
    @Composable
    fun LifeCycleAwareScreen() {

        /**
         * LocalLifecycleOwner.current
         *
         * Gives the lifecycle owner associated with this composable.
         * This could be:
         * - Activity
         * - Fragment
         * - Navigation destination
         */
        val lifecycleOwner = LocalLifecycleOwner.current

        /**
         * DisposableEffect
         *
         * Used for SIDE EFFECTS that need cleanup.
         *
         * Why DisposableEffect is needed:
         * - Composables can recompose many times
         * - Without this, observer would be added multiple times
         * - DisposableEffect guarantees:
         *      - Add observer only once
         *      - Remove observer automatically
         */
        DisposableEffect(lifecycleOwner) {

            // Create lifecycle observer (NO UI logic inside it)
            val observer = SimpleLifecycleObserver()

            // Attach observer to lifecycle
            lifecycleOwner.lifecycle.addObserver(observer)

            /**
             * onDispose()
             *
             * Called when:
             * - Composable leaves the screen
             * - LifecycleOwner changes
             * - UI is destroyed
             *
             * This prevents memory leaks and duplicate callbacks
             */
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text("LifeCycle Aware Compose Screen")
        }
    }
}