package com.goodarzi.kishvira.network.treatment

import com.goodarzi.kishvira.model.Treatment.Treatment
import com.goodarzi.kishvira.model.fire.Fire
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface TreatmentApi {
    @GET
    fun getTreatmentDetail(@Url url: String, @Header("api-version") version: Int, @Header("x-api-key") apiKey: String): Deferred<Response<Treatment>>

    @GET
    fun getFireDetail(@Url url: String, @Header("api-version") version: Int, @Header("x-api-key") apiKey: String): Deferred<Response<Fire>>

}