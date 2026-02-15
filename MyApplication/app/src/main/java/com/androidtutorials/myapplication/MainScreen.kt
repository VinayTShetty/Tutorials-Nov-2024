package com.androidtutorials.myapplication

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidtutorials.myapplication.ui.ItemList

/**
 * PaginationScreen is the main composable for displaying a paginated list.
 *
 * Responsibilities:
 * 1. Connects UI with the ViewModel (data + business logic)
 * 2. Triggers the initial page load when the screen first appears
 * 3. Listens for scroll events to load additional pages
 */
@Composable
fun PaginationScreen(
    /**
     * Lifecycle-aware ViewModel instance.
     *
     * vm: ListViewModel = viewModel()
     * -------------------------------
     * Using `viewModel()` ensures:
     * - The same instance survives recompositions
     * - It survives configuration changes like screen rotation
     * - Can be injected for testing if needed
     *
     * ❌ Avoid `ListViewModel()` here directly — it would create a new instance on every recomposition
     */
    vm: ListViewModel = viewModel()
) {

    /**
     * LazyListState remembers scroll position across recompositions.
     *
     * Needed for:
     * - Preserving scroll position when screen recomposes
     * - Detecting when the user reaches the end of the list
     */
    val listState = rememberLazyListState()

    /**
     * Initial load of data
     *
     * LaunchedEffect(Unit):
     * - Runs only once when this composable enters the composition
     * - Safe coroutine scope provided by Compose
     * - Prevents multiple API calls during recomposition
     */
    LaunchedEffect(Unit) {
        vm.loadNextpage()   // ✅ Load the first page of items
    }

    /**
     * Pagination listener
     *
     * PaginationEffect:
     * - Observes scroll changes using LazyListState
     * - Calls vm.loadNextpage() automatically when the user scrolls to the bottom
     *
     * Keeps UI and business logic separated.
     */
    PaginationEffect(
        listState = listState,
        totalItems = vm.items.size,
        onLoadMore = { vm.loadNextpage() }   // ✅ Trigger next page load
    )

    /**
     * Display the list UI
     *
     * ItemList:
     * - Pure UI composable
     * - Receives items and scroll state
     * - Does NOT handle API, pagination, or loading logic
     */
    ItemList(
        listState = listState,
        lazyitems = vm.items   // ✅ Pass current list of items from ViewModel
    )
}
