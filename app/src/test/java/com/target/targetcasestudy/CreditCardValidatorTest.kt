package com.target.targetcasestudy

import com.target.targetcasestudy.utils.Validators
import org.junit.Assert
import org.junit.Test


class CreditCardValidatorTest {
  @ExperimentalStdlibApi
  @Test
  fun `is credit card number valid`() {
    Assert.assertTrue(
      "valid credit card number should yield true",
      Validators.validateCreditCard("4539976741512043")
    )
  }
}
