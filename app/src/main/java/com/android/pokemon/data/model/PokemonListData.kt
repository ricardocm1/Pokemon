package com.android.pokemon.data.model

data class PokemonListData(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonData>?
)