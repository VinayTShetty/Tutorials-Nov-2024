package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidtutorials.androidhelloworld.R
import com.example.retrofitdemo.model.User
import com.example.retrofitdemo.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call API
        fetchUsers()
    }

    private fun fetchUsers() {

        // Step 1:
        // Get Call object from ApiService
        val call = RetrofitClient.apiService.getUsers()

        // Step 2:
        // Execute API asynchronously
        // IMPORTANT:
        // Never use execute() on main thread
        // call.enqueue(object: Callback<List<User>>{})
        // frist type this and ask autom sugeestion for implement members

        call.enqueue(object : Callback<List<User>> {

            // Called when API response is received (SUCCESS)
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                if (response.isSuccessful) {

                    // response.body() contains API data
                    val users = response.body()

                    users?.forEach {
                        Log.d("API_SUCCESS", "Name: ${it.name}, Email: ${it.email}")
                    }
                }
            }

            // Called when API fails (NO INTERNET / TIMEOUT / SERVER ERROR)
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
            }
        })
    }
}
