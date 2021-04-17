package com.android.pokemon.di

import com.android.pokemon.data.PokemonApi
import com.android.pokemon.data.PokemonRemoteDataSource
import com.android.pokemon.data.PokemonRemoteDataSourceImpl
import com.android.pokemon.data.repository.PokemonRepositoryImpl
import com.android.pokemon.domain.repository.PokemonRepository
import com.android.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.android.pokemon.domain.usecase.GetPokemonListUseCase
import com.android.pokemon.presentation.gallery.GalleryViewModel
import com.android.pokemon.network.RetrofitClient.getOrCreateRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel {
        GalleryViewModel(getPokemonListUseCase = get(), getPokemonDetailUseCase = get())
    }

    factory { GetPokemonListUseCase(repository = get()) }
    factory { GetPokemonDetailUseCase(repository = get()) }

    factory<PokemonRepository> { PokemonRepositoryImpl(remoteDataSource = get()) }

    factory<PokemonRemoteDataSource> {
        PokemonRemoteDataSourceImpl(
            pokemonApi = getOrCreateRetrofit().create(
                PokemonApi::class.java
            )
        )
    }
}