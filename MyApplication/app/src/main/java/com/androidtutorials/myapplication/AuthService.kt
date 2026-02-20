package com.androidtutorials.myapplication

class AuthService {

    fun login(username:String,password: String): Boolean{
        return username=="admin" && password=="1234"
    }
}