package com.androidtutorials.androidhelloworld

/* ============================================================
   DEPENDENCY INVERSION PRINCIPLE (DIP)
   ============================================================ */
/*
🔹 Definition (Formal):
----------------------
High-level modules should not depend on low-level modules.
Both should depend on abstractions.

Abstractions should not depend on details.
Details should depend on abstractions.

--------------------------------------------------

🔹 DIP (Dependency Inversion Principle)
⭐ Ultra-short definition

Don’t depend on a concrete class.
Depend on an interface.
----------------------------------------------------------
🔹 Layman Definition ⭐:
----------------------
Do NOT directly depend on a specific implementation.
Depend on an interface so that implementations can change
without breaking your code.
*/


/* ============================================================
   ❌ BAD DESIGN – Violates DIP
   ============================================================ */

/**
 * Low-level module
 * Handles email sending
 */
class EmailService {

    fun sendEmail(msg: String) {
        println("Sending Email: $msg")
    }
}

/**
 * ❌ High-level module directly depends on EmailService
 *
 * Problem:
 * - Tightly coupled
 * - If requirement changes (SMS / WhatsApp),
 *   this class MUST be modified
 */
class OrderService {

    private val emailService = EmailService()

    fun sendOrderStatus() {
        emailService.sendEmail("Order Delivered")
    }
}

/*
Problems:
---------
• OrderService depends on concrete EmailService
• Hard to change notification type
• Violates Dependency Inversion Principle
*/


/* ============================================================
   ✅ GOOD DESIGN – DIP APPLIED
   ============================================================ */

/**
 * Abstraction (Interface)
 *
 * High-level modules will depend on this,
 * NOT on concrete implementations.
 */
interface Notifier {
    fun notify(message: String)
}


/* ============================================================
   Low-level modules IMPLEMENT the abstraction
   ============================================================ */

/**
 * Email notification implementation
 */
class EmailNotifier : Notifier {

    override fun notify(message: String) {
        println("Email sent: $message")
    }
}

/**
 * WhatsApp notification implementation
 */
class WhatsAppNotifier : Notifier {

    override fun notify(message: String) {
        println("WhatsApp message sent: $message")
    }
}

/**
 * SMS notification implementation (easy to add)
 */
class SmsNotifier : Notifier {

    override fun notify(message: String) {
        println("SMS sent: $message")
    }
}


/* ============================================================
   High-level module depends on ABSTRACTION
   ============================================================ */

/**
 * ✅ This class follows DIP
 *
 * It does NOT know:
 * - Whether notification is Email / WhatsApp / SMS
 *
 * It only knows:
 * - Something will notify
 */
class SendOrderConfirmation(private val notifier: Notifier) {

    fun placeOrderNotification() {
        notifier.notify("Your order has been delivered")
    }
}


/* ============================================================
   Usage (Client decides implementation)
   ============================================================ */

fun main() {

    val emailOrder = SendOrderConfirmation(EmailNotifier())
    emailOrder.placeOrderNotification()

    val whatsappOrder = SendOrderConfirmation(WhatsAppNotifier())
    whatsappOrder.placeOrderNotification()

    val smsOrder = SendOrderConfirmation(SmsNotifier())
    smsOrder.placeOrderNotification()
}

/*
Benefits of DIP here:
---------------------
✔ High-level code is independent
✔ Easy to change notification type
✔ No modification required in SendOrderConfirmation
✔ Follows Open–Closed Principle as well
✔ Testable and scalable design
*/
