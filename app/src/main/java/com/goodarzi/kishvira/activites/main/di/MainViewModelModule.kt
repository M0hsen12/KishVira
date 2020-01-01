package com.goodarzi.kishvira.activites.main.di

import androidx.lifecycle.ViewModel
import com.goodarzi.kishvira.di.ViewModelKey
import com.goodarzi.kishvira.activites.main.viewModel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindLoginViewModel(viewModel: MainViewModel): ViewModel
}