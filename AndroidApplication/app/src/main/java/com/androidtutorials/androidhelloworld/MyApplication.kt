package com.androidtutorials.androidhelloworld

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 🚀 THIS IS THE FIRST CLASS THAT RUNS
 *
 * Android OS creates Application object FIRST
 * BEFORE any Activity, Service, Receiver
 *
 * @HiltAndroidApp
 * ➜ Triggers Hilt code generation
 * ➜ Creates SingletonComponent (App-level container)
 */

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}