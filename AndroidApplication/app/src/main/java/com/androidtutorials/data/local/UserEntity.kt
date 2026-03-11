package com.androidtutorials.data.local

import androidx.annotation.IdRes
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id:Int ,

    val name : String ,

    val email : String
)