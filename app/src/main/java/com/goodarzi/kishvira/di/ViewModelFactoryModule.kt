package com.goodarzi.kishvira.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodarzi.kishvira.viewModel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

@Binds
abstract fun bindViewModelFactory(factory: ViewModelProviderFactory):ViewModelProvider.Factory
}