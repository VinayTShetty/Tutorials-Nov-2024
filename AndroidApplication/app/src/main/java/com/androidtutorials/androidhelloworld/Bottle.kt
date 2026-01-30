package com.androidtutorials.androidhelloworld

/* ============================================================
   LISKOV SUBSTITUTION PRINCIPLE (LSP)
   Example using Bottle
   ============================================================ */

/*
LSP Rule (Easy to remember):
----------------------------
If replacing a Parent class with its Child class
causes incorrect behavior or bugs → LSP is BROKEN.
*/


/**
 * Parent class
 *
 * PROMISE:
 * Any Bottle allows the user to drink the liquid inside.
 */
open class Bottle {

    open fun drink() {
        println("Drinking liquid")
    }
}


/**
 * Child class – WaterBottle
 *
 * This class HONORS the promise of Bottle.
 * Replacing Bottle with WaterBottle works perfectly.
 *
 * ✔ Follows LSP
 */
class WaterBottle : Bottle() {

    override fun drink() {
        super.drink()
        println("Drinking water safely")
    }
}


/**
 * Child class – PetrolBottle
 *
 * ❌ This class BREAKS the promise of Bottle.
 * Petrol cannot be drunk.
 *
 * Even though PetrolBottle IS-A Bottle by inheritance,
 * it is NOT substitutable in real usage.
 *
 * ❌ Violates Liskov Substitution Principle
 */
class PetrolBottle : Bottle() {

    override fun drink() {
        super.drink()
        // ❌ Logical error:
        // Petrol is not drinkable.
        // This method should not exist for this class.
        println("⚠️ ERROR: Petrol cannot be drunk!")
    }
}


/*
Why PetrolBottle violates LSP:
------------------------------
• Parent class promises "drinkable liquid"
• Child class cannot fulfill that promise
• Replacing Bottle with PetrolBottle causes incorrect behavior
• This may lead to crashes, bugs, or unsafe logic

Correct Design Insight:
-----------------------
PetrolBottle should NOT extend Bottle.
Instead, responsibilities should be separated.
*/
