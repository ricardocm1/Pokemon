package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.PokemonMoveData

data class PokemonMove(
    val move: Move
)

fun PokemonMoveData?.mapToPokemonMove(): PokemonMove =
    PokemonMove(
        move = this?.move.mapToMove()
    )

fun List<PokemonMoveData>?.mapToPokemonMoveList(): List<PokemonMove> =
    this?.map { it.mapToPokemonMove() }.handleOptional()