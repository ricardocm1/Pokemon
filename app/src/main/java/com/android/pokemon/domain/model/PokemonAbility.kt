package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonAbilityData

data class PokemonAbility(
    val ability: Ability,
    val isHidden: Boolean,
    val slot: Int
)

fun PokemonAbilityData?.mapToPokemonAbility(): PokemonAbility =
    PokemonAbility(
        ability = this?.ability.mapToAbility(),
        isHidden = this?.isHidden.handleOptional(),
        slot = this?.slot.handleOptional(),
    )

fun List<PokemonAbilityData>?.mapToPokemonAbilityList(): List<PokemonAbility> =
    this?.map { it.mapToPokemonAbility() }.handleOptional()