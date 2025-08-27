package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidtutorials.androidhelloworld.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// @AndroidEntryPoint → Marks this Activity as a Hilt container,
// so it can receive injected dependencies (like ViewModel)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get ViewModel instance
        // ViewModelProvider(this) automatically uses Hilt’s factory under the hood
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Observe LiveData
        // Observe LiveData, gets called whenever data changes
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