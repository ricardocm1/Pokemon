package com.android.pokemon.data.repository

import androidx.lifecycle.LiveData
import com.android.pokemon.data.PokemonRemoteDataSource
import com.android.pokemon.data.model.PokemonDetailData
import com.android.pokemon.data.model.PokemonListData
import com.android.pokemon.domain.repository.PokemonRepository
import com.android.pokemon.network.Resource

class PokemonRepositoryImpl(private val remoteDataSource: PokemonRemoteDataSource) :
    PokemonRepository {

    override fun getPokemonList(offset: Int): LiveData<Resource<PokemonListData>> =
        remoteDataSource.getPokemonList(offset)

    override fun getPokemonDetail(id: Int): LiveData<Resource<PokemonDetailData>> =
        remoteDataSource.getPokemonDetail(id)

}
