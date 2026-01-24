package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("------------>"+reverseString("Interview"))
    }


    fun reverseString(input: String) : String{
        var result=""

        for(i in input.length-1 downTo 0){
            result=result+input[i]
        }
        return result
    }
}