package com.android.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonAbilityData(
    val ability: AbilityData?,
    @SerializedName("is_hidden") val isHidden: Boolean?,
    val slot: Int?
)
