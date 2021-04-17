package com.android.pokemon.data

import com.android.pokemon.data.model.PokemonDetailData
import com.android.pokemon.data.model.PokemonListData
import com.android.pokemon.network.networkCall

class PokemonRemoteDataSourceImpl(private val pokemonApi: PokemonApi) : PokemonRemoteDataSource {

    override fun getPokemonList(offset: Int) = networkCall<PokemonListData> {
        client = pokemonApi.getPokemonListAsync(offset, LIMIT)
    }

    override fun getPokemonDetail(id: Int) = networkCall<PokemonDetailData> {
        client = pokemonApi.getPokemonDetailAsync(id)
    }

}
