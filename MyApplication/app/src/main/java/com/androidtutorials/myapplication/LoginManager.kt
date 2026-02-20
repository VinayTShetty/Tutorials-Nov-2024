package com.androidtutorials.myapplication


/**
 * We are performing / Testing this class Logic not Auth Service class.
 */
class LoginManager (private val authService: AuthService){

    fun performLogin(userName: String,password: String): String{

        val sucess=authService.login(userName,password)
        return if(sucess){"Login Successful"}
        else{"Login Failed"}
    }
}