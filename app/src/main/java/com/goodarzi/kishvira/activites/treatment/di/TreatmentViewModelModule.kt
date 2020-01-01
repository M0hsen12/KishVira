package com.goodarzi.kishvira.activites.treatment.di

import androidx.lifecycle.ViewModel
import com.goodarzi.kishvira.activites.treatment.viewModel.TreatmentViewModel
import com.goodarzi.kishvira.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TreatmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TreatmentViewModel::class)
    abstract fun bindTreatmentViewModel(viewModel: TreatmentViewModel): ViewModel
}