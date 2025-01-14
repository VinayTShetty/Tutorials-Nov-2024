package com.androidtutorials.mvvm

import com.androidtutorials.myapplication.room.Item
import com.androidtutorials.myapplication.room.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao ) {
    val allItems:Flow<List<Item>> = itemDao.getAllItems()

    suspend fun insert(item: Item){
        itemDao.insertItem(item)
    }

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item){
        itemDao.deleteItem(item)
    }
}