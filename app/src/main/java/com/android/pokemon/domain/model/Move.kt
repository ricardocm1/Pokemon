package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.MoveData

data class Move(
    val name: String,
    val url: String
)

fun MoveData?.mapToMove(): Move =
    Move(
        name = this?.name.handleOptional(),
        url = this?.url.handleOptional()
    )
