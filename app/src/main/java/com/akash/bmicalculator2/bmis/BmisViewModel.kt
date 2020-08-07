package com.akash.bmicalculator2.bmis

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.bmicalculator2.Event
import com.akash.bmicalculator2.data.BmiRepositoryImpl
import com.akash.bmicalculator2.models.BMI
import kotlinx.coroutines.launch
import javax.inject.Inject

class BmisViewModel
@Inject
constructor(private val bmiRepositoryImpl: BmiRepositoryImpl) : ViewModel() {

    private val _deleteBmiDialog = MutableLiveData<Event<BMI>>()
    val deleteBmiDialog: LiveData<Event<BMI>> = _deleteBmiDialog

    val items: LiveData<List<BMI>> = bmiRepositoryImpl.allBmi

    fun openDeleteBmiDialog(view: View, bmi: BMI): Boolean {
        _deleteBmiDialog.value = Event(bmi)
        return false
    }

    fun deleteBmi(bmi: BMI) {
        viewModelScope.launch {
            bmiRepositoryImpl.deleteBmi(bmi)
        }
    }
}