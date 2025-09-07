package com.androidtutorials.di

import com.androidtutorials.data.remote.api.UserApi
import com.androidtutorials.data.repository.UserRepositoryImpl
import com.androidtutorials.domain.repository.UserRepository
import com.androidtutorials.domain.usecase.GetUsersUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  ServiceLocator {

    private val logging by lazy {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }

    private val okHttp by lazy {
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }

    private val userRepository: UserRepository by lazy { UserRepositoryImpl(userApi) }

    val getUsersUseCase: GetUsersUseCase by lazy { GetUsersUseCase(userRepository) }
}