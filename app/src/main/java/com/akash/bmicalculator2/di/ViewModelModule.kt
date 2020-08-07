package com.akash.bmicalculator2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.bmicalculator2.addnewbmi.AddNewBmiViewModel
import com.akash.bmicalculator2.bmis.BmisViewModel
import com.akash.bmicalculator2.result.ResultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BmisViewModel::class)
    abstract fun bindBmiViewModel(bmisViewModel: BmisViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddNewBmiViewModel::class)
    abstract fun bindAddNewBmiViewModel(addNewBmiViewModel: AddNewBmiViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultViewModel::class)
    abstract fun bindResultViewModel(resultViewModel: ResultViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}