package com.android.pokemon.domain.model

import com.android.pokemon.data.model.PokemonDetailData
import com.android.pokemon.extensions.handleOptional

data class PokemonDetail(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val order: Int,
    val isDefault: Boolean,
    val sprites: Sprite,
    val types: List<PokemonType>,
    val moves: List<PokemonMove>,
    val abilities: List<PokemonAbility>,
    val forms: List<PokemonForm>
)

fun PokemonDetailData?.mapToPokemonDetail(): PokemonDetail =
    PokemonDetail(
        id = this?.id.handleOptional(),
        name = this?.name.handleOptional(),
        baseExperience = this?.baseExperience.handleOptional(),
        height = this?.height.handleOptional(),
        weight = this?.weight.handleOptional(),
        order = this?.order.handleOptional(),
        isDefault = this?.isDefault.handleOptional(),
        sprites = this?.sprites.mapToSprite(),
        types = this?.types.mapToPokemonTypeList(),
        moves = this?.moves.mapToPokemonMoveList(),
        abilities = this?.abilities.mapToPokemonAbilityList(),
        forms = this?.forms.mapToPokemonFormList()
    )