package com.androidtutorials.navigation

sealed class Screens(val route:String){

    object ScreenForgotPass:Screens("FORGOT_PASS")
    object ScreenHome:Screens("HOME")
    object ScreenLogin:Screens("LOGIN")
    object ScreenRegister:Screens("REGISTER")
    object ScreenA:Screens("A_SCREEN")
    object ScreenB:Screens("B_SCREEN")

    object appRoute:Screens("APP")
    object authRoute:Screens("AUTH")
}
