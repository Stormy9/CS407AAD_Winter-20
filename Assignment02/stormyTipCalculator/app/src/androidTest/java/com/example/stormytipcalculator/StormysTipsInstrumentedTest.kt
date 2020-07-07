package com.example.stormytipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
@LargeTest

class StormysTipsInstrumentedTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    //===================================================================================
    @Test
    fun changeText_sameActivity() {

        onView(withId(R.id.txtInputBillTotal))
            .perform(typeText("100.00"), closeSoftKeyboard())

        // wait..... what all is happening here?
        //      as in, how many times is what being clicked for sure?   [=

        onView(withId(R.id.btnTipPlusPeople))
            .perform(scrollTo())
            .perform(click());
            onView(withId(R.id.btnTipPlusPeople))
                .perform(click())

        onView(withId(R.id.btnTipPlusPeople))
            .perform(scrollTo())
            .perform(click());
            onView(withId(R.id.btnTipPlusPercent))
                .perform(click())

        onView(withId(R.id.txtOutput_AmtPerPerson))
            .check(matches(withText("$40.33")))

    }
    //-----------------------------------------------------------------------------------
    @Test
    fun checking_changeHowManyPeople() {

        onView(withId(R.id.txtInputBillTotal))
            .perform (typeText("100.00"),
                closeSoftKeyboard())

        // still not 100% sure how to interpret this part....
        //      as in, how many times are we clicking this total?   [=

        onView(withId(R.id.btnTipPlusPeople))
            .perform(scrollTo())
            .perform(click());
            onView(withId(R.id.btnTipPlusPeople))
                .perform(click())

        onView(withId(R.id.btnTipPlusPeople))
            .perform(scrollTo())
            .perform(click());

        onView(withId(R.id.txtTipCurrentHowMany))
            .check(matches(withText("4")))

    }
    //-----------------------------------------------------------------------------------
    @Test
    fun checking_changeWhatTipPercent() {

        onView(withId(R.id.txtInputBillTotal))
            .perform (typeText("100.00"),
                closeSoftKeyboard())

        // still not 100% sure how to interpret this part....
        //      as in, how many times are we clicking this total?   [=

        onView(withId(R.id.btnTipPlusPercent))
            .perform(scrollTo())
            .perform(click());
        onView(withId(R.id.btnTipPlusPercent))
            .perform(click())

        onView(withId(R.id.btnTipPlusPercent))
            .perform(scrollTo())
            .perform(click());

        onView(withId(R.id.txtTipCurrentHowMany))
            .check(matches(withText("23%")))
    }
    //-----------------------------------------------------------------------------------
    // this one took adding some imports up-top.....
    @Test
    fun test_useAppContext() {

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        assertEquals("com.example.stormytipcalculator", appContext.packageName)
    }
    //-----------------------------------------------------------------------------------
}