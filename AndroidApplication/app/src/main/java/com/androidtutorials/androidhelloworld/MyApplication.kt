package com.androidtutorials.androidhelloworld

import android.app.Application
import com.androidtutorials.androidhelloworld.repository.UserRepository
import com.androidtutorials.androidhelloworld.retrofit.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication :Application() {

    lateinit var repository: UserRepository

    override fun onCreate() {
        super.onCreate()

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        repository = UserRepository(apiService) // Manual DI
    }
}

/**
Manually creating and injecting dependencies here (Retrofit → ApiService → UserRepository).
This is Manual Dependency Injection.
 */