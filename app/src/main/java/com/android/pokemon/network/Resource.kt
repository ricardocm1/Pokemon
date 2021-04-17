package com.android.pokemon.network

sealed class Resource<out T>(val data: T? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data)
    class Failure<T>(val throwable: Throwable) : Resource<T>()

    fun <R> resourceType(onSuccess: (T?) -> R?): Resource<R> = when (this) {
        is Success -> Success(onSuccess(data))
        is Failure -> Failure(throwable)
        is Loading -> Loading()
    }
}