package com.androidtutorials.androidhelloworld.diModule

import com.androidtutorials.androidhelloworld.repository.UserRepository
import com.androidtutorials.androidhelloworld.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// @Module → Tells Hilt this class provides dependencies
@Module
// @InstallIn(SingletonComponent::class) → Defines the scope of dependencies;
// SingletonComponent means dependencies live as long as the Application
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // @Provides → tells Hilt how to create an OkHttpClient
    // @Singleton → only one instance will exist throughout the app
    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        val logging=HttpLoggingInterceptor().apply {
            level=HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    // @Provides → tells Hilt how to create a Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    // Provides ApiService using Retrofit
    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    // Provides UserRepository with ApiService injected
    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepository(apiService)
    }
}