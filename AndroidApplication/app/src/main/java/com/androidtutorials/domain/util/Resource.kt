package com.androidtutorials.domain.util

// ✅ Simple result wrapper for Loading/Success/Error states across layers.
sealed class Resource <out T>{
    data object Loading : Resource<Nothing>()
    data class Sucess<T>(val  data :T):Resource<T>()
    data class Error<T>(val  message :String):Resource<T>()
}