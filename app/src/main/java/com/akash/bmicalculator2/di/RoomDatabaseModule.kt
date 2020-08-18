package com.akash.bmicalculator2.di

import android.app.Application
import com.akash.bmicalculator2.data.BmiRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomDatabaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideDatabase(application: Application) = BmiRoomDatabase.getDatabase(application)

    @Singleton
    @Provides
    @JvmStatic
    fun provideArticleDao(bmiRoomDatabase: BmiRoomDatabase) = bmiRoomDatabase.bmiDao()
}