package com.androidtutorials.myapplication

fun main() {
    val square: (Int) -> Int = { it * it }
    println(square(4))  // 16
}