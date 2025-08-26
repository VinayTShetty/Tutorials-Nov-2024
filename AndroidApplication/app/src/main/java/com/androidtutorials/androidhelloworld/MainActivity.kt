package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidtutorials.androidhelloworld.viewModel.UserViewModel
import com.androidtutorials.androidhelloworld.viewModel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create repository from Application class
        val repository = (application as MyApplication).repository
        // Pass repository to factory
        val factory = UserViewModelFactory(repository)
        // Get ViewModel instance
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        // Observe LiveData
        viewModel.users.observe(this, Observer { name ->
            println("UserName: $name")  // logcat output
        })

        // Trigger loading data
        viewModel.loadUsers()
    }
}

/**
    Injecting dependencies manually at the Activity level by pulling them from MyApplication.
    No external DI library is used → this is Pure Manual DI.
 */