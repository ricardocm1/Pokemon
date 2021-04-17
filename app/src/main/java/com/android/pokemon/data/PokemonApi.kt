package com.android.pokemon.data

import com.android.pokemon.data.model.PokemonDetailData
import com.android.pokemon.data.model.PokemonListData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val LIMIT: Int = 20

interface PokemonApi {

    @GET("pokemon")
    fun getPokemonListAsync(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Deferred<Response<PokemonListData>>

    @GET("pokemon/{id}")
    fun getPokemonDetailAsync(
        @Path("id") id: Int
    ): Deferred<Response<PokemonDetailData>>

}