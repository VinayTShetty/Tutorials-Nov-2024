package com.androidtutorials.androidhelloworld

import javax.inject.Inject

/**
 * Real implementation
 *
 * @Inject constructor
 * ➜ Tells Hilt HOW to create this class
 */
class SimpleAdder @Inject constructor() : Calculator{

    override fun add(a: Int, b: Int): Int {
        return a+b;
    }

}