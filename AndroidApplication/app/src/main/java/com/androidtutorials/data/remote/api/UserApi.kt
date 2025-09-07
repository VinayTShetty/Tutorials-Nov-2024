package com.androidtutorials.data.remote.api

import com.androidtutorials.data.remote.dto.UserDto
import retrofit2.http.GET

interface UserApi{
    // ✅ Retrofit interface declaring endpoints. No business logic here.
    @GET("users")
    suspend fun getUsers():List<UserDto>
}