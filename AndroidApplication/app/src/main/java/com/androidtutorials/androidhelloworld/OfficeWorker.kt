package com.androidtutorials.androidhelloworld

/* ============================================================
   INTERFACE SEGREGATION PRINCIPLE (ISP)
   Office / Corporate Example
   ============================================================ */

/*
ISP Definition:
---------------
A class should NOT be forced to implement methods
that it does not actually use.

Instead of one large interface,
create multiple small and focused interfaces.
*/


/* ============================================================
   ❌ BAD DESIGN – Violates ISP
   ============================================================ */

/**
 * ❌ Fat interface
 *
 * This interface mixes multiple responsibilities:
 * - Coding
 * - UI designing
 * - Coffee machine maintenance
 *
 * Any class implementing this interface
 * is FORCED to implement ALL methods,
 * even if they are not relevant.
 */
interface OfficeWorker {
    fun writeCode()
    fun designUI()
    fun repairCoffeMachine()
}

/**
 * ❌ Developer is forced to implement
 * coffee machine repair – which is NOT their job.
 */
class Developer : OfficeWorker {

    override fun writeCode() {
        println("Write application code")
    }

    override fun designUI() {
        println("Design UI screens")
    }

    override fun repairCoffeMachine() {
        // ❌ Unnecessary implementation
        // Developer should not handle this responsibility
        println("Not applicable, communicate to Admin team")
    }
}

/*
Problems with this design:
--------------------------
- Developer implements methods it does not need
- Leads to dummy / meaningless code
- Violates Interface Segregation Principle
- Difficult to maintain and scale
*/


/* ============================================================
   ✅ GOOD DESIGN – Follows ISP
   ============================================================ */

/**
 * Small, focused interface for coding responsibility
 */
interface Coder {
    fun writeCode()
}

/**
 * Small, focused interface for UI designing responsibility
 */
interface UIDesigner {
    fun designUI()
}

/**
 * Small, focused interface for coffee machine maintenance
 */
interface CoffeeMachineMaintainer {
    fun repairCoffeMachine()
}


/**
 * Frontend developer only writes code
 * Implements ONLY what is required
 */
class FrontendDeveloper : Coder {

    override fun writeCode() {
        println("Write frontend application code")
    }
}


/**
 * UI Design team handles UI-related work only
 */
class UIDesignTeam : UIDesigner {

    override fun designUI() {
        println("Design UI for the application")
    }
}


/**
 * Admin team handles office maintenance tasks
 */
class AdminTeam : CoffeeMachineMaintainer {

    override fun repairCoffeMachine() {
        println("Repairing coffee machine")
    }
}


/* ============================================================
   BENEFITS OF ISP
   ============================================================ */

/*
✔ Each class has a single, clear responsibility
✔ No unnecessary method implementations
✔ Code is easier to read and maintain
✔ Changes in one responsibility do not affect others
✔ Follows real-world role separation
*/


/* ============================================================
   INTERVIEW ONE-LINER
   ============================================================ */

/*
"Interface Segregation Principle ensures that
classes implement only the methods relevant
to their role, avoiding unnecessary dependencies."
*/
