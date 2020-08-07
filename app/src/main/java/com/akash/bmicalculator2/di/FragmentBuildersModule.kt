package com.akash.bmicalculator2.di

import com.akash.bmicalculator2.addnewbmi.AddNewBmiFragment
import com.akash.bmicalculator2.bmis.BmisFragment
import com.akash.bmicalculator2.result.ResultFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBmiFragemnt(): BmisFragment

    @ContributesAndroidInjector
    abstract fun contributeAddNewBmiFragment(): AddNewBmiFragment

    @ContributesAndroidInjector
    abstract fun contributeResultFragment(): ResultFragment

}