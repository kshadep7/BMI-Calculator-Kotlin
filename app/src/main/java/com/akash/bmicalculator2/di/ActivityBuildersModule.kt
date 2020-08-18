package com.akash.bmicalculator2.di

import com.akash.bmicalculator2.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity
}