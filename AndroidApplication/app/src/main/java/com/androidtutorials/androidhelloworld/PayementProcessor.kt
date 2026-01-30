package com.androidtutorials.androidhelloworld

/* ============================================================
   ❌ PROBLEM CODE – Violates Open–Closed Principle (OCP)
   ============================================================ */

/**
 * ❌ This class violates the Open–Closed Principle
 *
 * Why?
 * - Business logic depends on String values ("UPI", "CARD")
 * - Every new payment type requires MODIFYING this class
 * - High risk of bugs when changes are made
 */
class PaymentProcessorBad {

    fun pay(type: String) {
        if (type == "UPI") {
            println("Scan QR code to pay")
        } else if (type == "CARD") {
            println("Please swipe the card")
        }
        // ❌ If tomorrow NET_BANKING comes,
        // ❌ we must MODIFY this class again
    }
}

/*
Problems with above design:
- Class is NOT closed for modification ❌
- Violates SOLID → Open–Closed Principle ❌
- Tight coupling between logic & payment type ❌
*/


/* ============================================================
   ✅ REFACTORED CODE – Follows Open–Closed Principle (OCP)
   ============================================================ */

/**
 * STEP 1: Create an abstraction
 *
 * This interface defines WHAT to do,
 * not HOW to do it.
 *
 * PaymentProcessor will depend on this interface,
 * not on concrete implementations.
 */
interface Payment {
    fun pay()
}


/**
 * STEP 2: Concrete implementation for UPI payment
 *
 * This class EXTENDS behavior
 * without changing existing code.
 */
class UPIPayment : Payment {
    override fun pay() {
        println("Scan the QR code to pay via UPI")
    }
}


/**
 * STEP 2: Concrete implementation for Card payment
 */
class CardPayment : Payment {
    override fun pay() {
        println("Swipe the card to complete the transaction")
    }
}


/**
 * STEP 3: PaymentProcessor depends on abstraction
 *
 * ✅ CLOSED for modification
 * ✅ OPEN for extension
 *
 * We will NEVER touch this class again
 * when new payment types are added.
 */
class PaymentProcessor(private val payment: Payment) {

    fun processPayment() {
        payment.pay()
        println("Payment processing completed")
    }
}


/* ============================================================
   STEP 4: CLIENT CODE (Usage)
   ============================================================ */

fun main() {

    // Using UPI payment
    val upiProcessor = PaymentProcessor(UPIPayment())
    upiProcessor.processPayment()

    // Using Card payment
    val cardProcessor = PaymentProcessor(CardPayment())
    cardProcessor.processPayment()
}


/* ============================================================
   STEP 5: ADDING NEW PAYMENT (NO MODIFICATION REQUIRED)
   ============================================================ */

/**
 * New payment type added
 *
 * NOTE:
 * - No change in PaymentProcessor
 * - No change in existing code
 * - Just EXTENSION
 */
class NetBankingPayment : Payment {
    override fun pay() {
        println("Redirecting to Net Banking portal")
    }
}

/*
Benefits of this design:
✔ No if-else chains
✔ No modification of PaymentProcessor
✔ Easy to add new payment methods
✔ Follows Open–Closed Principle
✔ Clean, scalable, testable design
*/
