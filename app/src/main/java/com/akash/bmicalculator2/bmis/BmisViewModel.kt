package com.akash.bmicalculator2.bmis

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.akash.bmicalculator2.Event
import com.akash.bmicalculator2.data.BmiRepository
import com.akash.bmicalculator2.data.BmiRoomDatabase
import com.akash.bmicalculator2.models.BMI
import kotlinx.coroutines.launch

class BmisViewModel(application: Application) : AndroidViewModel(application) {

    private val bmiRepository: BmiRepository
    private val _deleteBmiDialog = MutableLiveData<Event<BMI>>()
    val deleteBmiDialog: LiveData<Event<BMI>> = _deleteBmiDialog

    init {
        val bmidao = BmiRoomDatabase.getDatabase(application.applicationContext).bmiDao()
        bmiRepository = BmiRepository(bmidao)
    }

    val items: LiveData<List<BMI>> = bmiRepository.allBmi

    fun openDeleteBmiDialog(view: View, bmi: BMI): Boolean {
        _deleteBmiDialog.value = Event(bmi)
        return false
    }

    fun deleteBmi(bmi: BMI) {
        viewModelScope.launch {
            bmiRepository.deleteBmi(bmi)
        }
    }
}