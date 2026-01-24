package com.example.retrofitdemo.model

// This data class represents ONE user object from API response
// Gson automatically maps JSON fields to these variables
data class User(

    // "id" comes from JSON → { "id": 1 }
    val id: Int,

    // "name" comes from JSON → { "name": "Leanne Graham" }
    val name: String,

    // "email" comes from JSON → { "email": "test@test.com" }
    val email: String
)
