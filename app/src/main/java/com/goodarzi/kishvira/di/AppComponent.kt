package com.goodarzi.kishvira.di

import android.app.Application
import com.goodarzi.kishvira.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {


    @Component.Builder
    interface Builder {


        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}