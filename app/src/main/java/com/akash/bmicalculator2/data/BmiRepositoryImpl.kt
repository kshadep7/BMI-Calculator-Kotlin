package com.akash.bmicalculator2.data

import androidx.lifecycle.LiveData
import com.akash.bmicalculator2.models.BMI
import javax.inject.Inject

class BmiRepositoryImpl
@Inject
constructor(private val bmiDao: BmiDao) : BmiRepository {

    override val allBmi: LiveData<List<BMI>> = bmiDao.getAllBmis()

    override suspend fun inserBmi(bmi: BMI) {
        bmiDao.inserBmi(bmi)
    }

    override suspend fun deleteBmi(bmi: BMI) {
        bmiDao.deleteBmi(bmi)
    }
}