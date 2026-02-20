package com.androidtutorials.myapplication

import org.junit.*
import org.junit.Assert.*

/**
 * This test class demonstrates important JUnit annotations:
 *
 * @BeforeClass
 * @AfterClass
 * @Before
 * @After
 * @Test
 * @Ignore
 *
 * These annotations control the lifecycle of test execution.
 */
class DiscountCalculatorLifecycleTest {

    private lateinit var calculator: DiscountCalculator

    /**
     * @Before
     *
     * Runs BEFORE each test method.
     * Used to initialize objects or prepare common test data.
     *
     * This ensures every test starts with a fresh object.
     */
    @Before
    fun setUp() {
        println("Before each test - Creating new DiscountCalculator")
        calculator = DiscountCalculator()
    }

    /**
     * @After
     *
     * Runs AFTER each test method.
     * Used to clean resources, close connections, or reset data.
     */
    @After
    fun tearDown() {
        println("After each test - Cleaning up")
    }

    /**
     * @Test
     *
     * Marks a method as a test case.
     * JUnit runs all methods annotated with @Test.
     */
    @Test
    fun applyDiscount_validInput_returnsCorrectResult() {

        val result = calculator.applyDiscount(100.0, 20)

        assertEquals(80.0, result, 0.001)
    }

    /**
     * @Test with expected exception (OLD STYLE)
     *
     * This tells JUnit:
     * "Test passes only if IllegalArgumentException is thrown."
     *
     * NOTE: Modern approach is assertThrows().
     */
    @Test(expected = IllegalArgumentException::class)
    fun applyDiscount_negativePrice_throwsException() {
        calculator.applyDiscount(-50.0, 10)
    }

    /**
     * @Ignore
     *
     * Skips this test temporarily.
     * Useful when:
     * - Feature not implemented yet
     * - Test is under development
     */
    @Ignore("Skipping until new discount logic is implemented")
    @Test
    fun applyDiscount_futureFeature_notImplementedYet() {
        val result = calculator.applyDiscount(100.0, 5)
        assertEquals(95.0, result, 0.001)
    }

    companion object {

        /**
         * @BeforeClass
         *
         * Runs ONLY ONCE before all test methods.
         * Must be static (hence inside companion object with @JvmStatic).
         *
         * Used for expensive setup:
         * - Database connection
         * - Network setup
         * - Heavy object creation
         */
        @BeforeClass
        @JvmStatic
        fun globalSetUp() {
            println("BeforeClass - Runs once before all tests")
        }

        /**
         * @AfterClass
         *
         * Runs ONLY ONCE after all test methods complete.
         *
         * Used for:
         * - Releasing shared resources
         * - Closing database
         */
        @AfterClass
        @JvmStatic
        fun globalTearDown() {
            println("AfterClass - Runs once after all tests")
        }
    }
}


/*
====================================================
LIFECYCLE ORDER (How JUnit Executes This Class)
====================================================

1️⃣ @BeforeClass      → Runs once
2️⃣ @Before           → Runs before each test
3️⃣ @Test             → Test executes
4️⃣ @After            → Runs after each test
   (Repeat 2–4 for every test method)
5️⃣ @AfterClass       → Runs once at the end

====================================================
WHY THESE ANNOTATIONS ARE IMPORTANT
====================================================

@Before      → Reset state before each test (Test isolation)
@After       → Cleanup logic
@BeforeClass → Expensive shared setup
@AfterClass  → Final cleanup
@Test        → Defines test case
@Ignore      → Temporarily disable test

====================================================
IMPORTANT CONCEPT: TEST ISOLATION
====================================================

Each test should:
✔ Not depend on other tests
✔ Run independently
✔ Have fresh state

That is why we create a new DiscountCalculator
inside @Before instead of sharing one object.

====================================================
This is complete lifecycle coverage in JUnit 4.
====================================================
*/


/*
=========================================================
WHY DO WE NEED companion object HERE?
=========================================================

Short Answer:
Because @BeforeClass and @AfterClass must be STATIC methods in JUnit 4.

Kotlin does NOT have static methods like Java.
So we use companion object + @JvmStatic to simulate static behavior.

---------------------------------------------------------
1️⃣ JUnit 4 RULE (Very Important)
---------------------------------------------------------

In JUnit 4:

@BeforeClass
@AfterClass

➡ MUST be static methods.
➡ Must run without creating class instance.

Why?

Because they run:
✔ BEFORE any test object is created
✔ AFTER all test objects are destroyed

So JUnit cannot call them on an instance.
It needs them at class level.

---------------------------------------------------------
2️⃣ Java vs Kotlin Difference
---------------------------------------------------------

In Java:

@BeforeClass
public static void globalSetUp() { }

Java supports static directly.

In Kotlin:

There is NO "static" keyword.

So we use:

companion object {
    @JvmStatic
    @BeforeClass
    fun globalSetUp() { }
}

---------------------------------------------------------
3️⃣ What is companion object?
---------------------------------------------------------

Definition:

A companion object is a special object inside a class
that holds class-level members (like static in Java).

Example:

class Example {
    companion object {
        fun test() { }
    }
}

You can call it as:
Example.test()

---------------------------------------------------------
4️⃣ Why @JvmStatic?
---------------------------------------------------------

Without @JvmStatic,
Kotlin generates something like:

DiscountCalculatorLifecycleTest.Companion.globalSetUp()

But JUnit expects:

DiscountCalculatorLifecycleTest.globalSetUp()

So @JvmStatic tells Kotlin:

"Generate real static method for Java compatibility."

---------------------------------------------------------
5️⃣ What happens if we remove companion object?
---------------------------------------------------------

If you write:

@BeforeClass
fun globalSetUp() { }

You will get error:

@BeforeClass method must be static

Because JUnit cannot call it.

---------------------------------------------------------
6️⃣ When DO we need companion object in tests?
---------------------------------------------------------

Only when using:

✔ @BeforeClass
✔ @AfterClass

You DO NOT need it for:
✔ @Before
✔ @After
✔ @Test
✔ @Ignore

Because those run on test instance.

---------------------------------------------------------
7️⃣ Execution Flow Internally
---------------------------------------------------------

JUnit does this:

1. Call static @BeforeClass
2. Create test instance
3. Call @Before
4. Run @Test
5. Call @After
6. Destroy instance
7. Repeat 2–6 for each test
8. Call static @AfterClass

Notice:
@BeforeClass runs BEFORE object creation.
So it cannot depend on instance variables.

---------------------------------------------------------
8️⃣ Important Interview Point
---------------------------------------------------------

Q: Why do we use companion object in JUnit 4 tests?

Answer:
Because @BeforeClass and @AfterClass must be static,
and Kotlin does not support static directly,
so we use companion object with @JvmStatic.

---------------------------------------------------------
9️⃣ JUnit 5 Difference (Modern)
---------------------------------------------------------

In JUnit 5, this restriction is removed.

You can write:

@BeforeAll
fun setup() { }

Without companion object,
if you use @TestInstance(TestInstance.Lifecycle.PER_CLASS)

So this static limitation is mainly JUnit 4.

---------------------------------------------------------

FINAL SUMMARY

companion object is needed ONLY to satisfy
JUnit 4 requirement that:

@BeforeClass and @AfterClass must be static.

Kotlin solution = companion object + @JvmStatic

=========================================================
*/