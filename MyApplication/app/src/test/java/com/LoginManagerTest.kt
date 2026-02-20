import com.androidtutorials.myapplication.AuthService
import com.androidtutorials.myapplication.LoginManager
import io.mockk.*
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Test

class LoginManagerTest {

    /*
    =============================================================
    OVERVIEW OF THIS TEST CLASS
    =============================================================

    We are testing ONLY LoginManager.

    We are NOT testing:
    - Real API
    - Real Database
    - Real AuthService logic

    Why?
    Because unit testing means:
    👉 Test one class in isolation.

    So we replace AuthService with a MOCK.

    MOCK = Fake object with no real logic.
    */

    // Fake dependency
    private val authService = mockk<AuthService>()

    // Injecting mock into class under test
    private val loginManager = LoginManager(authService)

    @After
    fun tearDown() {
        /*
        Runs after each test.
        Clears mock state.

        WHY?
        To make sure tests don’t affect each other.
        Each test must be independent.
        */
        clearAllMocks()
    }

    /*
    =============================================================
    1️⃣ STUBBING EXAMPLE
    =============================================================

    PURPOSE:
    Test SUCCESS path of LoginManager.

    WHAT THIS TEST PROVES:
    If AuthService returns TRUE,
    then LoginManager should return "Login Successful".

    WHAT ACTUALLY HAPPENS:
    - authService is fake.
    - We manually tell it:
        "When login('vinay','1234') is called → return true"
    - performLogin() uses that fake response.
    - We verify correct success message.

    THIS IS CALLED:
    ✅ STUBBING
    */
    @Test
    fun login_success_stubbing_example() {

        every { authService.login("vinay", "1234") } returns true

        val result = loginManager.performLogin("vinay", "1234")

        assertEquals("Login Successful", result)
    }

    /*
    =============================================================
    2️⃣ VERIFY INTERACTION
    =============================================================

    PURPOSE:
    Check if dependency method was called correctly.

    WHAT THIS TEST PROVES:
    - login() was called exactly once.
    - It was called with correct parameters.

    WHAT ACTUALLY HAPPENS:
    - We allow login() to return true for any input.
    - We call performLogin().
    - Then verify interaction.

    WHY IMPORTANT?
    In real apps:
    - Ensure API called once
    - Prevent duplicate DB writes
    - Ensure correct data sent

    THIS IS CALLED:
    ✅ INTERACTION TESTING
    */
    @Test
    fun verify_login_called() {

        every { authService.login(any(), any()) } returns true

        loginManager.performLogin("user", "pass")

        verify(exactly = 1) {
            authService.login("user", "pass")
        }
    }

    /*
    =============================================================
    3️⃣ ARGUMENT CAPTURE
    =============================================================

    PURPOSE:
    Inspect what values were passed to mock.

    WHAT THIS TEST PROVES:
    - performLogin() passed correct values to AuthService.

    WHAT ACTUALLY HAPPENS:
    - slot<String>() captures argument.
    - When login() is called → values stored in slot.
    - We assert captured values.

    WHY IMPORTANT?
    Real world use cases:
    - Check password encryption before API call
    - Check username trimmed
    - Check data transformed

    THIS IS CALLED:
    ✅ ARGUMENT CAPTURING
    */
    @Test
    fun capture_login_arguments() {

        val usernameSlot = slot<String>()
        val passwordSlot = slot<String>()

        every {
            authService.login(capture(usernameSlot), capture(passwordSlot))
        } returns true

        loginManager.performLogin("capturedUser", "capturedPass")

        assertEquals("capturedUser", usernameSlot.captured)
        assertEquals("capturedPass", passwordSlot.captured)
    }

    /*
    =============================================================
    4️⃣ RELAXED MOCK
    =============================================================

    PURPOSE:
    Reduce boilerplate when return value is not important.

    WHAT THIS TEST PROVES:
    - login() was called.
    - No need to define every { }.

    WHAT ACTUALLY HAPPENS:
    relaxed = true makes mock return default values:

        Boolean → false
        Int → 0
        String → ""

    WHY IMPORTANT?
    Useful when:
    - Only verifying method calls
    - Return value doesn't matter

    */
    @Test
    fun relaxed_mock_example() {

        val relaxedAuth = mockk<AuthService>(relaxed = true)

        val manager = LoginManager(relaxedAuth)

        manager.performLogin("abc", "xyz")

        verify {
            relaxedAuth.login("abc", "xyz")
        }
    }

    /*
    =============================================================
    5️⃣ SPY (PARTIAL MOCK)
    =============================================================

    PURPOSE:
    Use REAL object but still verify interactions.

    WHAT THIS TEST PROVES:
    - Real AuthService logic runs.
    - We can still verify method call.

    DIFFERENCE:
    mockk() → Completely fake object.
    spyk()  → Real object wrapped by MockK.

    WHEN TO USE?
    - Legacy systems
    - When partial mocking needed
    - When you want real execution

    */
    @Test
    fun spy_example() {

        val realAuthService = AuthService()

        val spyAuth = spyk(realAuthService)

        val manager = LoginManager(spyAuth)

        val result = manager.performLogin("admin", "1234")

        assertEquals("Login Successful", result)

        verify {
            spyAuth.login("admin", "1234")
        }
    }

    /*
    =============================================================
    6️⃣ BASIC FAILURE TEST
    =============================================================

    PURPOSE:
    Test FAILURE path of LoginManager.

    WHAT THIS TEST PROVES:
    If AuthService returns FALSE,
    LoginManager should return "Login Failed".

    WHY IMPORTANT?
    Always test:
    - Success case
    - Failure case
    - Edge cases

    This ensures full logic coverage.
    */
    @Test
    fun basic_mock_example() {

        val mockAuth = mockk<AuthService>()

        every { mockAuth.login(any(), any()) } returns false

        val manager = LoginManager(mockAuth)

        val result = manager.performLogin("any", "any")

        assertEquals("Login Failed", result)
    }
}