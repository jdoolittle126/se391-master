package com.android.neit.myapplication

import junit.framework.TestCase
import org.junit.Assert

class MainActivityTest : TestCase() {

    fun testCalculate() {
        val actual: Float = CalculateOperations.CalculateAnswer(4f, 20.5f)
        val expected: Float = 82f

        Assert.assertEquals("calculateAnswer Failed", actual.toDouble(), expected.toDouble(), 0.001)
    }


    fun testCalculateBad() {
        val actual: Float = CalculateOperations.CalculateAnswer(4f, 20.5f)
        val expected: Float = 89000f

        Assert.assertEquals("calculateAnswer Failed", actual.toDouble(), expected.toDouble(), 0.001)
    }
}