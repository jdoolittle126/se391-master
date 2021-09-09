package com.example.testinglab

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class MainActivityTest : TestCase() {

    @Test
    fun testCalculateMonthlyPayment() {
        val amountOfLoan = 100000.00
        val yearlyInterestRate = 0.05
        val numberOfYears = 30

        val returned = Calculations.determineMortagePayment(amountOfLoan, yearlyInterestRate / 12, numberOfYears * 12.0)
        val expected = 536.82

        Assert.assertEquals("testCalculateMonthlyPayment Failed", expected, returned, 0.01)
    }

    @Test
    fun testCalculateMonthlyPaymentBad() {
        val amountOfLoan = 100000.00
        val yearlyInterestRate = 0.05
        val numberOfYears = 30

        val returned = Calculations.determineMortagePaymentBad(amountOfLoan, yearlyInterestRate / 12, numberOfYears * 12.0)
        val expected = 536.82

        Assert.assertEquals("testCalculateMonthlyPayment Failed",expected, returned, 0.01)

    }
}