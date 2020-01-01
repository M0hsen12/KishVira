package com.goodarzi.kishvira.activites.fire.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.goodarzi.daggerchale.utilz.Result
import com.goodarzi.daggerchale.utilz.safeApiCall
import com.goodarzi.kishvira.model.fire.Fire
import com.goodarzi.kishvira.network.fire.FireApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class FireViewModel @Inject constructor(var fireApi: FireApi):ViewModel() {
    private val _fireDetail = MediatorLiveData<Fire>()
    private val _error = MediatorLiveData<String>()
    private var fireJob: Job? = null
    val fireDetail: LiveData<Fire> get() = _fireDetail
    val error: LiveData<String> get() = _error

    fun callApiMethod(url: String,version:Int,apiKey:String) {

        fireJob = CoroutineScope(Dispatchers.IO).launch {
            val value = getFireDetail(url,version,apiKey)
            when (value) {
                is Result.Success -> _fireDetail.postValue(value.data)
                is Result.Error -> _error.postValue(value.exception.message)
            }
        }
    }
    suspend fun getFireDetail(url: String,version:Int,apiKey:String) = safeApiCall(
        call = { fireCall(url,version,apiKey) },
        errorMessage = "check your network"
    )


    private suspend fun fireCall(url: String,version:Int,apiKey:String): Result<Fire> {
        val response = fireApi.getFireDetail(url,version,apiKey).await()

        if (response.body()!!.status==200) {
            return Result.Success(response.body()!!)
        }
//        return Result.Error(IOException("Error occurred during fetching user\nplease try again"))
        return  Result.Error(IOException(response.body()!!.message))
    }

    override fun onCleared() {
        super.onCleared()
        fireJob?.cancel()

    }
}