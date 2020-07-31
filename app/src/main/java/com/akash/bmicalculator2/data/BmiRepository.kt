package com.akash.bmicalculator2.data

import androidx.lifecycle.LiveData
import com.akash.bmicalculator2.models.BMI

class BmiRepository(private val bmiDao: BmiDao) {

    val allBmi: LiveData<List<BMI>> = bmiDao.getAllBmis()

    suspend fun inserBmi(bmi: BMI) {
        bmiDao.inserBmi(bmi)
    }

    suspend fun deleteBmi(bmi: BMI) {
        bmiDao.deleteBmi(bmi)
    }
}