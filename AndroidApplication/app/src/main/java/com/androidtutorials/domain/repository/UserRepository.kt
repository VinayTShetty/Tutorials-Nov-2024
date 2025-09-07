package com.androidtutorials.domain.repository

import com.androidtutorials.domain.model.User

// ✅ Repository Abstraction (Domain depends on an interface, not details).
// It declares WHAT the domain needs; data layer decides HOW to provide it.

interface UserRepository {
    suspend fun getUsers():List<User>
}