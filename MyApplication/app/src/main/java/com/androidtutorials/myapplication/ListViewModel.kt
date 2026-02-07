package com.androidtutorials.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){



    val items = mutableStateListOf<Int>()


    private var currentPage=0
    private val pageSize=20
    private var isLoading=false


    fun loadNextpage(){
        if(isLoading)
            return
        isLoading=true

        viewModelScope.launch {
            delay(1500)

            //Fake API response.
            val newItems=fetchItemFromApi(page=currentPage,size=pageSize)
            items.addAll(newItems)
            isLoading=false
            currentPage++

        }
    }

    private fun fetchItemFromApi( page:Int,size: Int) : List<Int> {
        val start= page*size
        return (start until start +size).toList()
    }
}