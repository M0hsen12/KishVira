package com.goodarzi.kishvira.activites.fire.di

import androidx.lifecycle.ViewModel
import com.goodarzi.kishvira.activites.fire.viewModel.FireViewModel
import com.goodarzi.kishvira.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FireViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FireViewModel::class)
    abstract fun bindLoginViewModel(viewModel: FireViewModel): ViewModel
}