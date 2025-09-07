package com.androidtutorials.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidtutorials.domain.model.User
import com.androidtutorials.domain.usecase.GetUsersUseCase
import com.androidtutorials.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    data class UiState(
        val loading: Boolean = false,
        val users: List<User> = emptyList(),
        val error: String? = null
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun load() {
        viewModelScope.launch {
            Log.d("UsersViewModel", "🔄 load() started - calling GetUsersUseCase")
            getUsersUseCase().collect { res ->
                when (res) {
                    is Resource.Loading -> {
                        Log.d("UsersViewModel", "⏳ Resource.Loading")
                        _state.update { it.copy(loading = true, error = null) }
                    }
                    is Resource.Sucess -> {
                        Log.d("UsersViewModel", "✅ Resource.Success: ${res.data.size} users fetched")
                        res.data.forEach { Log.d("UsersViewModel", "👤 User: $it") }
                        _state.update { it.copy(loading = false, users = res.data, error = null) }
                    }
                    is Resource.Error -> {
                        Log.e("UsersViewModel", "❌ Resource.Error: ${res.message}")
                        _state.update { it.copy(loading = false, error = res.message) }
                    }
                }
            }
        }
    }
}
