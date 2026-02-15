package com.androidtutorials.myapplication.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * This composable displays a scrollable list.
 *
 * @param listState -> Controls and observes scroll position.
 * Required for detecting pagination trigger.
 *
 * @param lazyitems -> List of items to display.
 * Comes from ViewModel state.
 */
@Composable
fun ItemList(
    listState: LazyListState,
    lazyitems: List<Int>
) {
    LazyColumn(
        state = listState, // Needed for pagination scroll detection
        contentPadding = PaddingValues(8.dp) // Space around list
    ) {

        /**
         * items() is a LazyColumn DSL function.
         *
         * It takes:
         * - a List
         * - a lambda to describe how each item UI looks
         *
         * It only composes visible items (performance optimization).
         */
        items(lazyitems) { item ->
            ItemCard(item)
        }
    }
}

/**
 * Represents a single row in the list.
 */
@Composable
fun ItemCard(item: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth() // Takes full width
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text(
            text = "Item $item",
            modifier = Modifier.padding(16.dp)
        )
    }
}
