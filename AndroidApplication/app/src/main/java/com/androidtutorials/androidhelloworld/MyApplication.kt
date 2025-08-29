package com.androidtutorials.androidhelloworld

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// @HiltAndroidApp:
// ----------------
// 1. Placed on Application class.
// 2. Triggers Hilt’s code generation.
// 3. Creates a base Application class that acts as the entry point
//    for dependency injection across the whole app.
// 4. This is REQUIRED for Hilt to work in any Android project.
@HiltAndroidApp
class MyApplication :Application() {
    // No need to write anything inside.
    // Hilt generates the required setup behind the scenes.
}