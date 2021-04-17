package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonFormData

data class PokemonForm(
    val name: String,
    val url: String
)

fun PokemonFormData?.mapToPokemonForm(): PokemonForm =
    PokemonForm(
        name = this?.name.handleOptional(),
        url = this?.url.handleOptional()
    )

fun List<PokemonFormData>?.mapToPokemonFormList(): List<PokemonForm> =
    this?.map { it.mapToPokemonForm() }.handleOptional()