package com.example.testinglab

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testCalculateMonthlyPayment() {

        val amountOfLoan = 100000.00
        val yearlyInterestRate = 0.05
        val numberOfYears = 30

        Espresso.onView(withId(R.id.AmountOfLoan)).perform(ViewActions.typeText(amountOfLoan.toString()))
        Espresso.onView(withId(R.id.AmountOfLoan)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.InterestRate)).perform(ViewActions.typeText(yearlyInterestRate.toString()))
        Espresso.onView(withId(R.id.InterestRate)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.NumberOfYears)).perform(ViewActions.typeText(numberOfYears.toString()))
        Espresso.onView(withId(R.id.NumberOfYears)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.CalculateMonthlyPayment)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.tvMonthlyPayment))
            .check(ViewAssertions.matches(ViewMatchers.withText("$536.82")))
    }

    @Test
    fun testCalculateMonthlyPaymentBad() {

        val amountOfLoan = 100000.00
        val yearlyInterestRate = 0.05
        val numberOfYears = 30

        Espresso.onView(withId(R.id.AmountOfLoan)).perform(ViewActions.typeText(amountOfLoan.toString()))
        Espresso.onView(withId(R.id.AmountOfLoan)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.InterestRate)).perform(ViewActions.typeText(yearlyInterestRate.toString()))
        Espresso.onView(withId(R.id.InterestRate)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.NumberOfYears)).perform(ViewActions.typeText(numberOfYears.toString()))
        Espresso.onView(withId(R.id.NumberOfYears)).perform(ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.CalculateInterestBad)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.tvMonthlyPayment))
            .check(ViewAssertions.matches(ViewMatchers.withText("$536.82")))
    }



}