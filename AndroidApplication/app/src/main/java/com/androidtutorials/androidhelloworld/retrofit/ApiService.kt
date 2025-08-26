package com.androidtutorials.androidhelloworld.retrofit

import com.androidtutorials.androidhelloworld.data.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}