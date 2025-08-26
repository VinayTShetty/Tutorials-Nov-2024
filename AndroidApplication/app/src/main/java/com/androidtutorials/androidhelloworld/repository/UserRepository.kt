package com.androidtutorials.androidhelloworld.repository

import com.androidtutorials.androidhelloworld.data.User
import com.androidtutorials.androidhelloworld.retrofit.ApiService

class UserRepository(private val apiService: ApiService){

    suspend fun fetchUsers() :List<User>{
        return apiService.getUsers()
    }
}

/**
Here **DI is applied via constructor injection.
Instead of UserRepository creating its own ApiService, you pass the dependency from outside.
✅ This makes UserRepository testable, because in tests you can pass a fake ApiService.
 */
