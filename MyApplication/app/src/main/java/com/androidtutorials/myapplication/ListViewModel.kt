package com.androidtutorials.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel holds business logic and state.
 *
 * Why ViewModel?
 * - Survives configuration changes
 * - Lifecycle aware
 * - Separates UI & logic
 */
class ListViewModel : ViewModel() {

    /**
     * mutableStateListOf()
     *
     * Special Compose-aware list.
     * When modified -> UI recomposes automatically.
     */
    val items = mutableStateListOf<Int>()

    private var currentPage = 0
    private val pageSize = 20

    /**
     * Prevents multiple API calls at same time.
     */
    private var isLoading = false

    /**
     * Called when:
     * - Screen first loads
     * - User scrolls to bottom
     */
    fun loadNextpage() {

        if (isLoading)
            return // Prevent duplicate API calls

        isLoading = true

        /**
         * viewModelScope:
         * - Coroutine scope tied to ViewModel lifecycle
         * - Automatically cancelled when ViewModel cleared
         */
        viewModelScope.launch {

            // Simulate network delay
            delay(1500)

            // Fake API response
            val newItems = fetchItemFromApi(
                page = currentPage,
                size = pageSize
            )

            items.addAll(newItems)

            isLoading = false
            currentPage++
        }
    }

    /**
     * Fake API function
     */
    private fun fetchItemFromApi(page: Int, size: Int): List<Int> {

        val start = page * size

        return (start until start + size).toList()
    }
}
