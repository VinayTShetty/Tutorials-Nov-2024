package com.androidtutorials.mvvm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.androidtutorials.myapplication.room.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ItemViewModel(private val Itemrepository: ItemRepository): ViewModel() {

    val allItems:StateFlow<List<Item>> = Itemrepository.allItems.map { it }.stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList()
    )

    fun addItem(name: String){
        viewModelScope.launch {
            Itemrepository.insert(Item(name = name))
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch {
            Itemrepository.deleteItem(item)
        }
    }

    fun updateItem(item: Item){
        viewModelScope.launch {
            Itemrepository.updateItem(item)
        }
    }

    /**
     * Explanation :- https://www.youtube.com/watch?v=MCqmeNBKV9k
     */
    class ItemViewModelFactory(private val repository: ItemRepository):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ItemViewModel::class.java)){
                return ItemViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return super.create(modelClass, extras)
        }
    }
}