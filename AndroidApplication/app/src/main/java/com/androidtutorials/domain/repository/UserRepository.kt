package com.androidtutorials.domain.repository

import com.androidtutorials.domain.model.User


interface UserRepository {

    suspend fun getUsers(): List<User>

}