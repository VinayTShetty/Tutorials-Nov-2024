package com.androidtutorials.domain.usecase

import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.repository.UserRepository
import com.androidtutorials.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

// ✅ Use Case: encapsulates one business action (fetch users).
// Great place to add business rules: sorting, filtering, validation, caching, etc.

class GetUsersUseCase(private val repository: UserRepository) {

    operator fun invoke(): Flow<Resource<List<User>>> = flow {
            emit(Resource.Loading)
        try {
            val users=repository.getUsers().sortedBy { it.name }
            emit(Resource.Sucess(users))
            // Example rule: sort alphabetically by name
        }catch (e:Exception){
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }
}
