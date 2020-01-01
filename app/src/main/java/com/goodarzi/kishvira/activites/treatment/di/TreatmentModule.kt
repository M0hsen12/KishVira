package com.goodarzi.kishvira.activites.treatment.di

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodarzi.kishvira.network.treatment.TreatmentApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class TreatmentModule {

    @Provides
    fun prodivesTreatmentApi(retrofit: Retrofit): TreatmentApi {
        return retrofit.create(TreatmentApi::class.java)
    }

}