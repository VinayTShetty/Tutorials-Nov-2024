package com.androidtutorials.androidhelloworld



/**
 * Simple class with business logic
 *
 * NOTE:
 * ❌ This class does NOT know about Hilt
 * ❌ No @Inject here
 * ✅ Plain Kotlin class
 */
class Calculator () {

    fun add (a:Int,b:Int) :Int{
        return a+b
    }
}