package com.android.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailData(
    val id: Int?,
    val name: String?,
    @SerializedName("base_experience") val baseExperience: Int?,
    val height: Int?,
    val weight: Int?,
    val order: Int?,
    @SerializedName("is_default") val isDefault: Boolean?,
    val sprites: SpriteData?,
    val types: List<PokemonTypeData>?,
    val moves: List<PokemonMoveData>?,
    val abilities: List<PokemonAbilityData>?,
    val forms: List<PokemonFormData>?,
)
