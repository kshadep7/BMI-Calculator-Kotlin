package com.akash.bmicalculator2.bmis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.akash.bmicalculator2.data.BmiRepository
import com.akash.bmicalculator2.data.BmiRoomDatabase
import com.akash.bmicalculator2.models.BMI

class BmisViewModel(application: Application) : AndroidViewModel(application) {

    private val bmiRepository: BmiRepository

    init {
        val bmidao = BmiRoomDatabase.getDatabase(application.applicationContext).bmiDao()
        bmiRepository = BmiRepository(bmidao)
    }

    val items: LiveData<List<BMI>> = bmiRepository.allBmi
}