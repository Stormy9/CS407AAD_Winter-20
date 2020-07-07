package com.example.stormytipcalculator

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test


class StormysTipsUnitTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    //===================================================================================
    // first three are based off Prof Lucas' example...
    //-----------------------------------------------------------------------------------
    @Test
    fun tipCalculatorShouldEqualTwentyFourPerPerson() {

        val totalPerPerson: Double = StormysTipCalculator().calculatePerEachTotal(100.00, 5, 20)

        assertEquals(24.00, totalPerPerson, 0.01)

    }
    //-----------------------------------------------------------------------------------
    @Test
    fun tipCalculatorShouldEqualTwentyNineFiftyPerPerson() {

        val totalPerPerson: Double = StormysTipCalculator().calculatePerEachTotal(50.00, 2, 18)

        assertEquals(29.50, totalPerPerson, 0.01)
    }
    //-----------------------------------------------------------------------------------
    @Test
    fun tipCalculatorShouldEqualTwentyNineFiftyTwoPerPerson() {

        val totalPerPerson: Double = StormysTipCalculator().calculatePerEachTotal(72.00, 3, 23)

        assertEquals(29.52, totalPerPerson, 0.01)
    }
    //-----------------------------------------------------------------------------------
    // now based off a classmate's examples.....
    //-----------------------------------------------------------------------------------
    @Test
    fun figurePerPersonCost_ShouldBeTwentyFiveSeventySeven() {

        val newTipCalculator = StormysTipCalculator()

        val perPersonAmount = newTipCalculator.calculatePerEachTotal(42.95, 2, 20)

        assertEquals(25.77, perPersonAmount, 0.01)
    }
    //-----------------------------------------------------------------------------------
    // yeah i know i'm doing some weird amounts for tests but they're real-life, hahaha
    @Test
    fun figureForOne_ShouldBeTwentyOne() {

        val newTipCalculator = StormysTipCalculator()

        val youPayThis = newTipCalculator.calculatePerEachTotal(17.95, 1, 17 )

        assertEquals( 21.00, youPayThis, 0.01)
    }
    //===================================================================================
}