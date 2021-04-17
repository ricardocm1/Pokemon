package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonTypeData

data class PokemonType(
    val slot: Int,
    val type: Type
)

fun PokemonTypeData?.mapToPokemonType(): PokemonType =
    PokemonType(
        slot = this?.slot.handleOptional(),
        type = this?.type.mapToType()
    )

fun List<PokemonTypeData>?.mapToPokemonTypeList(): List<PokemonType> =
    this?.map { it.mapToPokemonType() }.handleOptional()