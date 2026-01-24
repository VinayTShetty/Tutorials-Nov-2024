package com.example.retrofitdemo.network

import com.example.retrofitdemo.model.User
import retrofit2.Call
import retrofit2.http.GET

// This interface defines all API endpoints
interface ApiService {

    // GET request
    // Full URL = baseUrl + "users"
    @GET("users")

    // Call<List<User>> means:
    // API will return a LIST of User objects
    fun getUsers(): Call<List<User>>
}
