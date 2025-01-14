package com.androidtutorials.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(@PrimaryKey(autoGenerate = true) val id:Int=0,val name:String)
