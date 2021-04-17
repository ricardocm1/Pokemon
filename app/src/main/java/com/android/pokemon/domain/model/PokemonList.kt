package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonData
import com.android.pokemon.data.model.PokemonListData

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Pokemon>
)

fun PokemonListData?.mapToPokemonList(): PokemonList =
    PokemonList(
        count = this?.count.handleOptional(),
        next = this?.next.handleOptional(),
        previous = this?.previous.handleOptional(),
        results = this?.results.mapToPokemonList(),
    )

fun List<PokemonData>?.mapToPokemonList(): List<Pokemon> =
    this?.map { it.mapToPokemon() }.handleOptional()