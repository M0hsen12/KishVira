package com.goodarzi.kishvira.activites.fire.di

import com.goodarzi.kishvira.network.fire.FireApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FireModule {
    @Provides
    fun prodivesMainApi(retrofit: Retrofit): FireApi {
        return retrofit.create(FireApi::class.java)
    }
}