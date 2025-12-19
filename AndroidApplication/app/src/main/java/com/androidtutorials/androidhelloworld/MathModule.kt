package com.androidtutorials.androidhelloworld

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 🧠 Hilt Module
 *
 * @Module
 * ➜ Tells Hilt: "Dependencies are provided here"
 *
 * @InstallIn(SingletonComponent::class)
 * ➜ Dependency lives as long as Application
 */
@Module
@InstallIn(SingletonComponent::class)
object MathModule {

    /**
     * @Provides
     * ➜ Tells Hilt HOW to create Calculator
     *
     * @Singleton
     * ➜ Same Calculator instance reused everywhere
     */
    @Provides
    fun provideCalculator(): Calculator {
        return Calculator()
    }

}