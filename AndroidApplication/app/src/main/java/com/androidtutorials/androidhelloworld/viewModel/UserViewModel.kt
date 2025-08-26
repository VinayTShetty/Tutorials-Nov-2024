package com.androidtutorials.androidhelloworld.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.androidtutorials.androidhelloworld.data.User
import com.androidtutorials.androidhelloworld.repository.UserRepository
import kotlinx.coroutines.*

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun loadUsers() {

        viewModelScope.launch {
            try {
                val response = repository.fetchUsers()
                _users.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

/**
constructor injection: The UserRepository is injected instead of being created inside ViewModel.
Makes UserViewModel independent of the actual data source.
 */

class UserViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/**
This is factory-based DI.
Since ViewModel is created by the Android framework, you can’t directly pass arguments (like repository) into its constructor.
The Factory acts as a bridge for dependency injection into ViewModel.
 */
