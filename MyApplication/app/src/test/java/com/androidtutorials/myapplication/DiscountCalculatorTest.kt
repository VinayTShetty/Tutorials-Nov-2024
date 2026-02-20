package com.androidtutorials.myapplication

import org.junit.Assert.*
import org.junit.Test

/**
 * This class contains unit tests for DiscountCalculator.
 *
 * A Unit Test verifies that a small piece of code (usually a function)
 * behaves exactly as expected.
 *
 * Here we are testing the applyDiscount() function.
 */
class DiscountCalculatorTest {

    // Creating object of the class we want to test
    private val calculator = DiscountCalculator()

    /**
     * @Test annotation tells JUnit:
     * "This is a test method. Run it during testing."
     */
    @Test
    fun applyDiscount_shouldReturnCorrectValue_whenValidInput() {

        // Arrange → Prepare input data
        val price = 100.0
        val discount = 10

        // Act → Call the function
        val result = calculator.applyDiscount(price, discount)

        // Assert → Verify expected result matches actual result

        /**
         * assertEquals(expected, actual, delta)
         *
         * expected → What we expect
         * actual   → What method returned
         * delta    → Allowed difference (for Double precision issues)
         *
         * We use delta for floating point comparison.
         */
        assertEquals(90.0, result, 0.001)
    }

    @Test
    fun applyDiscount_shouldReturnSamePrice_whenDiscountZero() {

        val result = calculator.applyDiscount(200.0, 0)

        // Verifies expected == actual
        assertEquals(200.0, result, 0.001)
    }

    @Test
    fun applyDiscount_shouldReturnZero_whenDiscountHundredPercent() {

        val result = calculator.applyDiscount(500.0, 100)

        assertEquals(0.0, result, 0.001)
    }

    @Test
    fun applyDiscount_shouldThrowException_whenPriceNegative() {

        /**
         * assertThrows verifies that a specific exception is thrown.
         *
         * If exception is NOT thrown → test FAILS.
         * If correct exception is thrown → test PASSES.
         */
        assertThrows(IllegalArgumentException::class.java) {
            calculator.applyDiscount(-100.0, 10)
        }
    }

    @Test
    fun applyDiscount_shouldThrowException_whenDiscountInvalid() {

        assertThrows(IllegalArgumentException::class.java) {
            calculator.applyDiscount(100.0, 200)
        }
    }

    // ------------------------------
    // Additional Important Assertions
    // ------------------------------

    @Test
    fun example_assertTrue_and_assertFalse() {

        val result = calculator.applyDiscount(100.0, 10)

        /**
         * assertTrue(condition)
         * Passes if condition is TRUE.
         */
        assertTrue(result == 90.0)

        /**
         * assertFalse(condition)
         * Passes if condition is FALSE.
         */
        assertFalse(result == 100.0)
    }

    @Test
    fun example_assertNotEquals() {

        val result = calculator.applyDiscount(100.0, 10)

        /**
         * assertNotEquals(expected, actual, delta)
         * Passes if values are NOT equal.
         */
        assertNotEquals(100.0, result, 0.001)
    }

    @Test
    fun example_assertNotNull() {

        val result = calculator.applyDiscount(100.0, 10)

        /**
         * assertNotNull(value)
         * Passes if value is NOT null.
         */
        assertNotNull(result)
    }

    @Test
    fun example_assertNull() {

        val nullableValue: String? = null

        /**
         * assertNull(value)
         * Passes if value IS null.
         */
        assertNull(nullableValue)
    }

    @Test
    fun example_assertSame_vs_assertNotSame() {

        val obj1 = "Hello"
        val obj2 = obj1
        val obj3 = "Hello"

        /**
         * assertSame(expected, actual)
         * Checks if both references point to SAME object in memory.
         */
        assertSame(obj1, obj2)

        /**
         * assertNotSame(expected, actual)
         * Checks if references are DIFFERENT.
         */
        assertNotSame(obj1, obj3)
    }
}