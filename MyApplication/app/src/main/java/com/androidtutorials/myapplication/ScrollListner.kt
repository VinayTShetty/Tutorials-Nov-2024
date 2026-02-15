package com.androidtutorials.myapplication

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

/**
 * This composable listens to scroll changes.
 *
 * It detects when user reaches bottom
 * and triggers loading more data.
 */
@Composable
fun PaginationEffect(
    listState: LazyListState,
    totalItems: Int,
    onLoadMore: () -> Unit
) {

    /**
     * LaunchedEffect restarts when:
     * - listState changes
     * - totalItems changes
     */
    LaunchedEffect(listState, totalItems) {

        /**
         * snapshotFlow converts Compose state into Flow.
         *
         * Here we observe:
         * Last visible item index.
         */
        snapshotFlow {
            listState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()
                ?.index
        }.collect { lastVisibleIndex ->

            /**
             * If last visible item is the last item in list,
             * trigger pagination.
             */
            if (lastVisibleIndex == totalItems - 1) {
                onLoadMore()
            }
        }
    }
}
