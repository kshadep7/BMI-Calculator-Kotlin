package com.akash.bmicalculator2.data.source

import androidx.lifecycle.LiveData
import com.akash.bmicalculator2.data.BmiRepository
import com.akash.bmicalculator2.models.BMI

class FakeBmiRepository(var bmis: MutableList<BMI> = mutableListOf()) : BmiRepository {

    suspend fun getListOfBmis() = bmis

    override val allBmi: LiveData<List<BMI>>
        get() = TODO("Not yet implemented")

    override suspend fun inserBmi(bmi: BMI) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBmi(bmi: BMI) {
        TODO("Not yet implemented")
    }

}
