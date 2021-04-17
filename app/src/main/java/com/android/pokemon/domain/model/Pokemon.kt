package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonData

class Pokemon(
    val name: String,
    val url: String
) {
    fun getId(): Int {
        val array = url.split("/")
        return array[array.size - 2].toInt()
    }
}

fun PokemonData?.mapToPokemon(): Pokemon =
    Pokemon(
        name = this?.name.handleOptional(),
        url = this?.url.handleOptional()
    )
