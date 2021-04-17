package com.android.pokemon.domain.repository

import androidx.lifecycle.LiveData
import com.android.pokemon.data.model.PokemonDetailData
import com.android.pokemon.data.model.PokemonListData
import com.android.pokemon.network.Resource

interface PokemonRepository {
    fun getPokemonList(offset: Int): LiveData<Resource<PokemonListData>>
    fun getPokemonDetail(id: Int): LiveData<Resource<PokemonDetailData>>
}