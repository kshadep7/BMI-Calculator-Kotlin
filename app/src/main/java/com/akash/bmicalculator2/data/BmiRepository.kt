package com.akash.bmicalculator2.data

import androidx.lifecycle.LiveData
import com.akash.bmicalculator2.models.BMI

interface BmiRepository {
    val allBmi: LiveData<List<BMI>>

    suspend fun inserBmi(bmi: BMI)
    suspend fun deleteBmi(bmi: BMI)
}