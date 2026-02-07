package com.androidtutorials.myapplication

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidtutorials.myapplication.ui.ItemList

@Composable
fun PaginationScreen(vm: ListViewModel = viewModel()) {

    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        vm.loadNextpage()
    }

    PaginationEffect(listState=listState, totalItems = vm.items.size , onLoadMore = {vm.loadNextpage()})

    ItemList(listState=listState,items=vm.items)

}