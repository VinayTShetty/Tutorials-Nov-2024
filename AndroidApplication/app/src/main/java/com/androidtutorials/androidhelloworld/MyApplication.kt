package com.androidtutorials.androidhelloworld

import android.app.Application
import com.androidtutorials.androidhelloworld.repository.UserRepository
import com.androidtutorials.androidhelloworld.retrofit.ApiService
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// @HiltAndroidApp → Generates base classes needed for Hilt DI
// Triggers Hilt code generation, creates App-level SingletonComponent
@HiltAndroidApp
class MyApplication :Application() {

    lateinit var repository: UserRepository

    override fun onCreate() {
        super.onCreate()
        // No need to manually create dependencies here (Hilt handles it)
    }
}
