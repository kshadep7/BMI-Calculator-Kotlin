package com.akash.bmicalculator2.di.module

import android.content.Context
import com.akash.bmicalculator2.data.BmiRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class BmiDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        BmiRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBmiDao(bmiRoomDatabase: BmiRoomDatabase) = bmiRoomDatabase.bmiDao()
}