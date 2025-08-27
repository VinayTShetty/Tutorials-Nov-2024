package com.androidtutorials.androidhelloworld.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope

import com.androidtutorials.androidhelloworld.data.User
import com.androidtutorials.androidhelloworld.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

// @HiltViewModel → Marks this ViewModel as injectable via Hilt
// @Inject constructor → Hilt knows how to create this ViewModel by injecting UserRepository
@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    // Loads users asynchronously using viewModelScope (lifecycle-aware CoroutineScope)
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
 * Removed UserViewModel factory as Hilt automatically handles it.
 */