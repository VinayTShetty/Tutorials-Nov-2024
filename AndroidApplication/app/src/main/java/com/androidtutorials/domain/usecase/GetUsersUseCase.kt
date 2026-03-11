package com.androidtutorials.domain.usecase

import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository){

    suspend operator  fun invoke() : List<User>{
     return  repository.getUsers()
    }
}