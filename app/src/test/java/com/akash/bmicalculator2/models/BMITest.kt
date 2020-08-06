package com.akash.bmicalculator2.models

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/** Using Given, When, Then mneumonic **/

class BMITest {
    //given
    private val bmi1 = BMI(height = 170, weight = 70, date = "03.08.2020")

    @Test
    fun getCalculatedbmi_returnsBmi() {
        // when
        val calculatedBmi = bmi1.bmi()
        // then
        assertThat(calculatedBmi, `is`(24.221453f))
    }
}