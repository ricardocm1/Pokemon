package com.android.pokemon.presentation.gallery

import androidx.lifecycle.*
import com.android.pokemon.domain.model.Pokemon
import com.android.pokemon.domain.model.PokemonDetail
import com.android.pokemon.domain.model.PokemonList
import com.android.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.android.pokemon.domain.usecase.GetPokemonListUseCase
import com.android.pokemon.network.Resource
import com.android.pokemon.presentation.model.PokemonPresentation

class GalleryViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private var offset = 0
    private var currentItemPosition = 0
    private var currentItemPagePosition = 0
    private val loadPokemonList: MutableLiveData<Int> = MutableLiveData()
    private val loadPokemonDetail: MutableLiveData<Int> = MutableLiveData()

    var pokemonList: MediatorLiveData<MutableList<PokemonPresentation>> = MediatorLiveData()

    val detailsLoaded: MutableLiveData<Int> = MutableLiveData()
    val isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }
    val hasReachedEnd = MutableLiveData<Boolean>().apply {
        value = false
    }

    private var resultPokemonList: LiveData<Resource<PokemonList>> =
        Transformations.switchMap(loadPokemonList) { offset ->
            getPokemonListUseCase(offset)
        }

    private var pokemonDetail: LiveData<Resource<PokemonDetail>> =
        Transformations.switchMap(loadPokemonDetail) { id ->
            getPokemonDetailUseCase(id)
        }

    init {
        pokemonList.addSource(resultPokemonList) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { info ->
                        offset += info.results.size

                        pokemonList.postValue(info.results.map {
                            PokemonPresentation(
                                it.getId(),
                                it.name
                            )
                        }
                            .toMutableList())

                        loadPokemonDetail(info.results.first())
                    }
                }
                is Resource.Loading -> {
                    isLoading.postValue(true)
                }
                is Resource.Failure -> {
                    isLoading.postValue(false)
                }
            }
        }

        pokemonList.addSource(pokemonDetail) { resource ->
            if (resource is Resource.Success) {
                resource.data?.let { pokemonDetail ->

                    pokemonList.value?.firstOrNull { it.name == pokemonDetail.name }?.run {
                        setDetails(pokemonDetail)
                    }

                    detailsLoaded.postValue(currentItemPosition)
                    currentItemPosition++

                    resultPokemonList.value?.data?.let { info ->
                        if (currentItemPagePosition < info.results.size - 1) {
                            currentItemPagePosition++
                            loadPokemonDetail(info.results[currentItemPagePosition])
                        } else {
                            isLoading.postValue(false)
                            hasReachedEnd.postValue(info.next.isEmpty())
                        }
                    }
                }
            }
        }
    }

    fun loadPokemonList() {
        if (hasReachedEnd.value == true) return

        currentItemPagePosition = 0
        pokemonList.value = mutableListOf()

        loadPokemonList.postValue(offset)
    }

    private fun loadPokemonDetail(pokemon: Pokemon) {
        loadPokemonDetail.postValue(pokemon.getId())
    }

    val showErrorMessage: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(resultPokemonList) {
            postValue(it is Resource.Failure)
        }
    }

}