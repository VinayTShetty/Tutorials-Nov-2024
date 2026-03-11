package com.androidtutorials.data.remote

import com.androidtutorials.data.model.UserDto
import retrofit2.http.GET


interface  ApiService{

    @GET("users")
    suspend fun getusers() : List<UserDto>
}