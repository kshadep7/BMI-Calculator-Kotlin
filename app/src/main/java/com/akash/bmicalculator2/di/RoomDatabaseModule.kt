package com.akash.bmicalculator2.di

import android.app.Application
import com.akash.bmicalculator2.data.BmiRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = BmiRoomDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideArticleDao(bmiRoomDatabase: BmiRoomDatabase) = bmiRoomDatabase.bmiDao()
}