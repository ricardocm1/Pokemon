package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.TypeData

data class Type(
    val name: String,
    val url: String
)

fun TypeData?.mapToType(): Type =
    Type(
        name = this?.name.handleOptional(),
        url = this?.url.handleOptional()
    )
