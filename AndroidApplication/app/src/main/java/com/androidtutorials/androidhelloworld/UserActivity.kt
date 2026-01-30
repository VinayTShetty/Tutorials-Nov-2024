package com.androidtutorials.androidhelloworld

/**
 * ❌ BAD DESIGN (Violates SRP)
 *
 * This class is doing MULTIPLE jobs:
 * 1. API call
 * 2. Database operation
 * 3. UI rendering
 *
 * Problem:
 * - If API changes → class changes
 * - If DB changes → class changes
 * - If UI changes → class changes
 *
 * 👉 One class = MANY reasons to change ❌
 */
class UserActivity {

    fun loadUser() {
        // API Call logic
    }

    fun saveUserToDB() {
        // Database logic
    }

    fun showUser() {
        // UI logic
    }
}

/**
 * 🚫 Too many responsibilities:
 * - Networking
 * - Persistence
 * - UI
 *
 * This violates Single Responsibility Principle
 */

// ----------------------------------------------------------------------

/**
 * ✅ GOOD DESIGN (Follows SRP)
 *
 * Each class has ONLY ONE reason to change.
 * Each class has ONLY ONE responsibility.
 */

// Handles ONLY user data fetching (API responsibility)
class UserRepository {

    fun loadUser() {
        // API Call logic
    }
}

// Handles ONLY database-related operations
class UserDBLogic {

    fun saveUserToDB() {
        // Database save logic
    }
}

// Handles ONLY UI-related work
class UserView {

    fun showUser() {
        // UI rendering logic
    }
}
