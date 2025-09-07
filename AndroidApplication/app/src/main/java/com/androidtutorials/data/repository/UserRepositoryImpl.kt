package com.androidtutorials.data.repository

import com.androidtutorials.data.mapper.toDomain
import com.androidtutorials.data.remote.api.UserApi
import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.repository.UserRepository

class UserRepositoryImpl (private val userApi: UserApi) :UserRepository {

    override suspend fun getUsers(): List<User> {
        return userApi.getUsers().map { it.toDomain() }
    }

}