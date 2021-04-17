package com.android.pokemon.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.pokemon.domain.model.PokemonList
import com.android.pokemon.domain.model.mapToPokemonList
import com.android.pokemon.domain.repository.PokemonRepository
import com.android.pokemon.network.Resource

class GetPokemonListUseCase(private val repository: PokemonRepository) {

    operator fun invoke(offset: Int): LiveData<Resource<PokemonList>> {
        return Transformations.map(repository.getPokemonList(offset)) { resource ->
            resource.resourceType { data ->
                data?.mapToPokemonList()
            }
        }
    }

}