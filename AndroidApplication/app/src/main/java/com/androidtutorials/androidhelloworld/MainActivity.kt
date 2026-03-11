package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.androidtutorials.data.local.UserDatabase
import com.androidtutorials.data.remote.RetrofitClient
import com.androidtutorials.data.repository.UserRepositoryImpl
import com.androidtutorials.domain.repository.UserRepository
import com.androidtutorials.domain.usecase.GetUsersUseCase
import com.androidtutorials.presentation.adapter.UserAdapter
import com.androidtutorials.presentation.viewmodel.UserViewModel
import com.androidtutorials.presentation.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var user_ViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Room DataBase

        val database = Room.databaseBuilder(applicationContext, UserDatabase::class.java,"user_db").build()

        val dao = database.userDao()
        val api = RetrofitClient.apiService

        val repository = UserRepositoryImpl(api,dao)

        val useCase = GetUsersUseCase(repository)
        val factory = UserViewModelFactory(useCase)

        user_ViewModel= ViewModelProvider(this,factory)[UserViewModel::class.java]

        user_ViewModel.loadUsers()
        user_ViewModel.users.observe(this){ users ->
            recyclerView.adapter = UserAdapter(users)
        }
    }
}




