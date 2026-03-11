package com.androidtutorials.data.repository

import com.androidtutorials.data.local.UserDao
import com.androidtutorials.data.mapper.convertDtoToEntity
import com.androidtutorials.data.mapper.convertEntityToDomain
import com.androidtutorials.data.remote.ApiService
import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.repository.UserRepository


class UserRepositoryImpl(
    private val api: ApiService,
    private val dao: UserDao,
) : UserRepository {
    override suspend fun getUsers(): List<User> {

        val apiUsers = api.getusers()

        val entities = apiUsers.map { userDto ->
            convertDtoToEntity(userDto)
        }

        dao.insertUsers(entities)

        val dbUsers = dao.getUsers()

        return dbUsers.map { userEntity ->
            convertEntityToDomain(userEntity)
        }
    }

}