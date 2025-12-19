package com.androidtutorials.androidhelloworld

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt Module
 *
 * @Module
 * ➜ Required container for binding rules
 *
 * @InstallIn(SingletonComponent::class)
 * ➜ Application-wide lifetime
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class MathModule {
    /**
     * @Binds
     * ➜ Tells Hilt:
     *   "When Adder is requested,
     *    use SimpleAdder"
     *
     * NOTE:
     * • Method is abstract
     * • Method body is NEVER executed
     */
    @Binds
    abstract fun bindAdder(imple: SimpleAdder) :Calculator

}