package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.AbilityData

data class Ability(
    val name: String,
    val url: String
)

fun AbilityData?.mapToAbility(): Ability =
    Ability(
        name = this?.name.handleOptional(),
        url = this?.url.handleOptional()
    )
