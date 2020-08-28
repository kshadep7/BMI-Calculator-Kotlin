package com.akash.bmicalculator2.bmis

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.bmicalculator2.Event
import com.akash.bmicalculator2.data.BmiRepositoryImpl
import com.akash.bmicalculator2.models.BMI
import kotlinx.coroutines.launch

// for view models -> no need to use view model factory
// we can use directly @ViewModelInject annotation
// that does the job for us to inject
class BmisViewModel @ViewModelInject constructor(private val bmiRepositoryImpl: BmiRepositoryImpl) :
    ViewModel() {

    // removed this as we are initializing it in constructor
//    private val bmiRepositoryImpl: BmiRepositoryImpl
    private val _deleteBmiDialog = MutableLiveData<Event<BMI>>()
    val deleteBmiDialog: LiveData<Event<BMI>> = _deleteBmiDialog

// removing this as we have the database in the hilt graph
/*
    init {
        val bmidao = BmiRoomDatabase.getDatabase(application.applicationContext).bmiDao()
        bmiRepositoryImpl = BmiRepositoryImpl(bmidao)
    }
*/

    val items: LiveData<List<BMI>> = bmiRepositoryImpl.allBmi

    fun openDeleteBmiDialog(view: View, bmi: BMI): Boolean {
        _deleteBmiDialog.postValue(Event(bmi))
        return false
    }

    fun deleteBmi(bmi: BMI) {
        viewModelScope.launch {
            bmiRepositoryImpl.deleteBmi(bmi)
        }
    }
}