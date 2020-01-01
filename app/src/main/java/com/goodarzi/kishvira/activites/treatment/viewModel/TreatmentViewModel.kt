package com.goodarzi.kishvira.activites.treatment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.goodarzi.daggerchale.utilz.Result
import com.goodarzi.daggerchale.utilz.safeApiCall
import com.goodarzi.kishvira.model.Treatment.Treatment
import com.goodarzi.kishvira.model.fire.Fire
import com.goodarzi.kishvira.network.treatment.TreatmentApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class TreatmentViewModel @Inject constructor(var treatmentApi: TreatmentApi) : ViewModel() {
    private val _treatmentDetail = MediatorLiveData<Treatment>()
    private val _error = MediatorLiveData<String>()
    private var treatmentJob: Job? = null

    val treatmentDetail: LiveData<Treatment> get() = _treatmentDetail
    val error: LiveData<String> get() = _error

    private val _fireDetail = MediatorLiveData<Fire>()
    private var fireJob: Job? = null
    val fireDetail: LiveData<Fire> get() = _fireDetail


    fun callApiMethod(url: String, version: Int, apiKey: String, isHistory: Boolean) {
        if (!isHistory) {
            treatmentJob = CoroutineScope(Dispatchers.IO).launch {
                val value = treatmentCall(url, version, apiKey)
                when (value) {
                    is Result.Success -> _treatmentDetail.postValue(value.data)
                    is Result.Error -> _error.postValue(value.exception.message)
                }
            }
        } else {

            fireJob = CoroutineScope(Dispatchers.IO).launch {
                val value = getFireDetail(url, version, apiKey)
                when (value) {
                    is Result.Success -> _fireDetail.postValue(value.data)
                    is Result.Error -> _error.postValue(value.exception.message)
                }
            }
        }

    }

    suspend fun treatmentCall(url: String, version: Int, apiKey: String) = safeApiCall(
        call = { getTreatmentDetail(url, version, apiKey) },
        errorMessage = "check your network"
    )

    suspend fun getFireDetail(url: String, version: Int, apiKey: String) = safeApiCall(
        call = { fireCall(url, version, apiKey) },
        errorMessage = "check your network"
    )

    private suspend fun getTreatmentDetail(
        url: String,
        version: Int,
        apiKey: String
    ): Result<Treatment> {
        val response = treatmentApi.getTreatmentDetail(url, version, apiKey).await()

        if (response.body()!!.status == 200) {
            return Result.Success(response.body()!!)
        }
//        return Result.Error(IOException("Error occurred during fetching user\nplease try again"))
        return Result.Error(IOException(response.body()!!.message))
    }


    private suspend fun fireCall(url: String, version: Int, apiKey: String): Result<Fire> {
        val response = treatmentApi.getFireDetail(url, version, apiKey).await()

        if (response.body()!!.status == 200) {
            return Result.Success(response.body()!!)
        }
//        return Result.Error(IOException("Error occurred during fetching user\nplease try again"))
        return Result.Error(IOException(response.body()!!.message))
    }

    override fun onCleared() {
        super.onCleared()
        treatmentJob?.cancel()
        fireJob?.cancel()

    }
}