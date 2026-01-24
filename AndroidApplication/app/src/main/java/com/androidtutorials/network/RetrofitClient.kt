package com.example.retrofitdemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // BASE URL of API
    // IMPORTANT:
    // Must end with '/'
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Logging interceptor
    // Used ONLY for debugging
    // It logs request & response in Logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
        // BODY → logs headers + response body
    }

    // OkHttp client
    // Retrofit internally uses OkHttp for network calls
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // attach logger
        .build()

    // Retrofit instance
    // This is the CORE object
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)                         // API base URL
        .client(okHttpClient)                      // HTTP client
        .addConverterFactory(GsonConverterFactory.create())
        // Converts JSON → Kotlin objects
        .build()

    // Expose ApiService to rest of app
    // This is what Activities/VMs will use
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
