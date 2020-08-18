package com.akash.bmicalculator2.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.bmicalculator2.Event
import com.akash.bmicalculator2.data.BmiRepositoryImpl
import com.akash.bmicalculator2.models.BMI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultViewModel
@Inject
constructor(private val bmiRepositoryImpl: BmiRepositoryImpl) : ViewModel() {


    private val _saveBmiEvent = MutableLiveData<Event<Unit>>()
    val saveBmiEvent: LiveData<Event<Unit>> = _saveBmiEvent

    private val _resultBmi = MutableLiveData<BMI>()
    private val resultBmi: LiveData<BMI> = _resultBmi

    private val _resultCalculatedBmi = MutableLiveData<Float>()
    val resultCalculatedBmi: LiveData<Float> = _resultCalculatedBmi

    fun getBmi(bmi: BMI?) {
        _resultBmi.value = bmi
        _resultCalculatedBmi.value = calculateBmi(bmi)
    }

    private fun calculateBmi(bmi: BMI?): Float {
        return bmi?.bmi() ?: 0f
    }

    fun saveBmi() {
        resultBmi.value?.let { _bmi ->
            viewModelScope.launch(Dispatchers.IO) {
                bmiRepositoryImpl.inserBmi(_bmi)
                _saveBmiEvent.postValue(Event(Unit))
            }
        }
    }
}