package com.goodarzi.kishvira.di


import com.goodarzi.kishvira.activites.fire.di.FireModule
import com.goodarzi.kishvira.activites.fire.di.FireViewModelModule
import com.goodarzi.kishvira.activites.fire.ui.FireActivity
import com.goodarzi.kishvira.activites.main.di.MainModule
import com.goodarzi.kishvira.activites.main.di.MainViewModelModule
import com.goodarzi.kishvira.activites.main.ui.MainActivity
import com.goodarzi.kishvira.activites.treatment.di.TreatmentModule
import com.goodarzi.kishvira.activites.treatment.di.TreatmentViewModelModule
import com.goodarzi.kishvira.activites.treatment.ui.TreatmentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [
            FireViewModelModule::class,
            FireModule::class
        ]
    )
    abstract fun contributeFireActivity(): FireActivity

    @ContributesAndroidInjector(
        modules = [
            TreatmentViewModelModule::class,
            TreatmentModule::class
        ]
    )
    abstract fun contributeTreatmentActivity(): TreatmentActivity


}