package com.androidtutorials.myapplication

// Compose UI test imports
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick

// JUnit imports
import org.junit.Rule
import org.junit.Test


class CounterScreenTest {

    // This rule sets up Compose testing environment
    // It launches a Compose container for testing UI
    @get:Rule
    val composeTestRule = createComposeRule()


    // Test 1: Verify initial state
    @Test
    fun counter_initialValue_is_zero() {

        // Set the composable under test
        composeTestRule.setContent {
            CounterScreen()
        }

        // Find node using testTag
        // Assert its text equals expected value
        composeTestRule
            .onNodeWithTag("counter-text-tag")
            .assertTextEquals("Count : 0")
    }


    // Test 2: Verify button click increments value
    @Test
    fun counter_buttonClick_incrementsValue() {

        composeTestRule.setContent {
            CounterScreen()
        }

        // Step 1: Find button and simulate click
        composeTestRule
            .onNodeWithTag("increment-button-tag")
            .performClick()

        // Step 2: Verify UI updated correctly
        composeTestRule
            .onNodeWithTag("counter-text-tag")
            .assertTextEquals("Count : 1")
    }
}