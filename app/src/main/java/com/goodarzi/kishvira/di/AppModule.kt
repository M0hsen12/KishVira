package com.goodarzi.kishvira.di

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.goodarzi.kishvira.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl("http://core.iloss.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun ProvideRequestManger(): RequestOptions =
        RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)


    @Singleton
    @Provides
    fun provideGlide(application: Application, requestOptions: RequestOptions): RequestManager =
        Glide.with(application)
            .setDefaultRequestOptions(requestOptions)


    @Singleton
    @Provides
    fun apiKey(): String = "676bdb1ce2d84276b8874a41f143c739"


}