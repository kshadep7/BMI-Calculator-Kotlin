package com.akash.bmicalculator2.addnewbmi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akash.bmicalculator2.Event
import com.akash.bmicalculator2.R
import com.akash.bmicalculator2.models.BMI
import com.akash.bmicalculator2.utils.getNowInString
import java.util.*
import javax.inject.Inject

class AddNewBmiViewModel
@Inject
constructor() : ViewModel() {

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private val _calculateBmiEvent = MutableLiveData<Event<Unit>>()
    val calculateBmiEvent: LiveData<Event<Unit>> = _calculateBmiEvent

    // Two-way databinding, exposing MutableLiveData
    val height = MutableLiveData<String>()

    // Two-way databinding, exposing MutableLiveData
    val weight = MutableLiveData<String>()

    lateinit var bmi: BMI

    private fun checkValueOfCorrectness(height: String?, weight: String?): Boolean {
        return when {
            height.isNullOrEmpty() -> {
                _snackbarText.value = Event(R.string.height_is_empty)
                false
            }
            weight.isNullOrEmpty() -> {
                _snackbarText.value = Event(R.string.weight_is_empty)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun createBmiInstance(height: String, weight: String) = BMI(
        0,
        height.toInt(),
        weight.toInt(),
        Date().getNowInString()
    )

    fun calculateBmi() {
        val currentHeight = height.value
        val currentWeight = weight.value

        if (checkValueOfCorrectness(currentHeight, currentWeight)) {
            bmi = createBmiInstance(currentHeight!!, currentWeight!!)
            _calculateBmiEvent.value = Event(Unit)
        }
    }
}