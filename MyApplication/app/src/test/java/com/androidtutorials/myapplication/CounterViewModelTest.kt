package com.androidtutorials.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit test for ViewModel.
 * This runs in local JVM (not on Android device).
 */
class CounterViewModelTest {

    /**
     * This rule forces LiveData to run synchronously.
     *
     * Why needed?
     * LiveData normally requires Android Main Thread.
     * Unit tests do not have Main Looper.
     *
     * This rule replaces the executor and avoids:
     * "Method getMainLooper not mocked" error.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setup() {
        viewModel = CounterViewModel()
    }

    /**
     * Backticks allow readable test names.
     * Kotlin supports spaces in function names using ``
     */
    @Test
    fun `initial counter value should be 0`() {
        assertEquals(0, viewModel.counter.value)
    }

    @Test
    fun `counter should increment when called`() {
        viewModel.incrementCounter()
        assertEquals(1, viewModel.counter.value)
    }

    @Test
    fun `counter should increment multiple times`() {
        viewModel.incrementCounter()
        viewModel.incrementCounter()
        viewModel.incrementCounter()
        assertEquals(3, viewModel.counter.value)
    }
}