package com.goodarzi.daggerchale.utilz

import android.util.Log
import java.io.IOException
import java.lang.Exception

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Log.e("eee",e.message!!)
    Result.Error(IOException(errorMessage, e))
}

val <T> T.exhaustive: T get() = this