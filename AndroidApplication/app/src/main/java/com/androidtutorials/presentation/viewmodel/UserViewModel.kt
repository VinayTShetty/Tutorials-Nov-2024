package com.androidtutorials.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel ( private val getUsersUseCase: GetUsersUseCase) : ViewModel(){


    private val _users = MutableLiveData<List<User>>()

     val users : LiveData<List<User>> = _users

    fun loadUsers(){
        viewModelScope.launch {
            _users.value = getUsersUseCase()
        }
    }
}