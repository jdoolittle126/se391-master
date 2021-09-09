package com.android.neit.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntentInstrumentedTest {

    @Rule
    @JvmField
    var mActivityRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun checkIntentPassing() {
        onView(withId(R.id.txtNumber1)).perform(typeText("5"))
        onView(withId(R.id.txtNumber2)).perform(typeText("10"))
        onView(withId(R.id.txtNumber2)).perform(closeSoftKeyboard())
        onView(withId(R.id.btnSendToNextScreen)).perform(click())
        intended(hasComponent(ResultsActivity::class.java.name))
    }


}