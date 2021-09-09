package com.android.neit.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkCalculation() {
        onView(withId(R.id.txtNumber1)).perform(typeText("5"))
        onView(withId(R.id.txtNumber1)).perform(closeSoftKeyboard())
        onView(withId(R.id.txtNumber2)).perform(typeText("10"))
        onView(withId(R.id.txtNumber2)).perform(closeSoftKeyboard())
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.tvSentResult)).check(matches(withText("50.0")))
    }

    @Test
    fun checkCalculationBad() {
        onView(withId(R.id.txtNumber1)).perform(typeText("5"))
        onView(withId(R.id.txtNumber1)).perform(closeSoftKeyboard())
        onView(withId(R.id.txtNumber2)).perform(typeText("10"))
        onView(withId(R.id.txtNumber2)).perform(closeSoftKeyboard())
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.tvSentResult)).check(matches(withText("52.0")))
    }

    @Test
    fun checkResultsActivity() {
        onView(withId(R.id.txtNumber1)).perform(typeText("5"))
        onView(withId(R.id.txtNumber1)).perform(closeSoftKeyboard())
        onView(withId(R.id.txtNumber2)).perform(typeText("10"))
        onView(withId(R.id.txtNumber2)).perform(closeSoftKeyboard())
        onView(withId(R.id.btnSendToNextScreen)).perform(click())
        onView(withId(R.id.tvSentValue)).check(matches(isDisplayed()))
    }


}