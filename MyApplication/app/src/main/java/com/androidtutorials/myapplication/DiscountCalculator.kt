package com.androidtutorials.myapplication

class DiscountCalculator {

    fun applyDiscount(price: Double,discountPrice:Int): Double{
        require(price>0){"Price  Cannot be Negative "}
        require(discountPrice in 0..100){"Invalid discount Price"}
        return price -(price*discountPrice/100)
    }
}