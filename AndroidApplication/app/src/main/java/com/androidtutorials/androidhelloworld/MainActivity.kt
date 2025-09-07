package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidtutorials.di.ServiceLocator
import com.androidtutorials.presentation.ui.UserScreen
import com.androidtutorials.presentation.viewmodel.UsersViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm : UsersViewModel = viewModel(factory = object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UsersViewModel(ServiceLocator.getUsersUseCase) as T
                }
            })
            val uistate=vm.state.collectAsState()
            LaunchedEffect(Unit) {
                vm.load()
            }
            UserScreen(state = uistate.value)
        }
    }
}