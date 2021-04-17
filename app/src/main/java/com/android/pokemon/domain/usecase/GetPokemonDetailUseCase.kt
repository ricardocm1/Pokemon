package com.android.pokemon.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.android.pokemon.domain.model.PokemonDetail
import com.android.pokemon.domain.model.mapToPokemonDetail
import com.android.pokemon.domain.repository.PokemonRepository
import com.android.pokemon.network.Resource

class GetPokemonDetailUseCase(private val repository: PokemonRepository) {

    operator fun invoke(id: Int): LiveData<Resource<PokemonDetail>> {
        return Transformations.map(repository.getPokemonDetail(id)) { resource ->
            resource.resourceType { data ->
                data?.mapToPokemonDetail()
            }
        }
    }

}