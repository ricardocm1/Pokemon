package com.android.pokemon.network

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Response

class CallHandler<RESPONSE : Any> {
    lateinit var client: Deferred<Response<RESPONSE>>

    fun makeCall(): MutableLiveData<Resource<RESPONSE>> {
        val result = MutableLiveData<Resource<RESPONSE>>()
        result.value = Resource.Loading()

        GlobalScope.launch {
            try {
                val response = client.await()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        result.value = Resource.Success(response.body())
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        result.value = Resource.Failure(Exception())
                    }
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    result.postValue(Resource.Failure(exception))
                }
                exception.printStackTrace()
            }
        }

        return result
    }

}

fun <RESPONSE : Any> networkCall(block: CallHandler<RESPONSE>.() -> Unit): MutableLiveData<Resource<RESPONSE>> =
    CallHandler<RESPONSE>().apply(block).makeCall()