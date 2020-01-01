package com.goodarzi.kishvira.activites.main.di

import com.goodarzi.kishvira.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    @Provides
    fun prodivesMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}