package com.akash.bmicalculator2.di

import android.app.Application
import com.akash.bmicalculator2.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        RoomDatabaseModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: MyApp?) {
        TODO("Not yet implemented")
    }
}