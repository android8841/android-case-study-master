package com.target.targetcasestudy.utils

import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
object Validators {
    @ExperimentalStdlibApi
    fun validateCreditCard(creditCardNumber: String): Boolean {
        var lastDigit: Int
        var sum = 0
        var creditCardDigits = creditCardNumber.toCharArray().map {
            Integer.parseInt(it.toString())
        }.also {
            var digitsList = it.toMutableList()
            if (digitsList.size < 13 || digitsList.size > 19) {
                return false
            }
            lastDigit = digitsList.removeLast()
            digitsList.reverse()
            for (n in digitsList.indices) {
                if (n % 2 == 0) {
                    digitsList[n].apply {
                        if (this * 2 > 9) {
                            digitsList[n] = this * 2 - 9;
                        } else {
                            digitsList[n] = this * 2
                        }
                    }
                }
                sum += digitsList[n]
            }
        }
        sum += lastDigit
        return (sum % 10 == 0)
    }
}