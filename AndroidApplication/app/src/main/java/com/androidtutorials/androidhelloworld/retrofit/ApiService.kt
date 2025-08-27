package com.androidtutorials.androidhelloworld.retrofit

import com.androidtutorials.androidhelloworld.data.User
import retrofit2.http.GET

interface ApiService {
    // @GET → Retrofit annotation that performs HTTP GET on "users" endpoint
    @GET("users")
    suspend fun getUsers(): List<User>
}