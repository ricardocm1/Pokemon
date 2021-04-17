package com.android.pokemon.network

import com.android.pokemon.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_DELAY = 30L

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getOrCreateRetrofit(): Retrofit {
        return retrofit ?: run {
            val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build().apply {
                    retrofit = this
                }
        }
    }
}