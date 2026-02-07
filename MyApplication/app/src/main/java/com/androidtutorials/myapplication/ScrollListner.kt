package com.androidtutorials.myapplication

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow


@Composable
fun PaginationEffect(listState: LazyListState, totalItems: Int, onLoadMore: () -> Unit) {

    LaunchedEffect(listState, totalItems) {

        //Observec Scroll changes as Flow
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastvisibleIndex ->

            // Trigger Pagination when last item is visible
            if(lastvisibleIndex==totalItems-1){
                onLoadMore()
            }

        }
    }

}
