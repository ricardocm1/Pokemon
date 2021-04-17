package com.android.pokemon.domain.model

import com.android.pokemon.extensions.handleOptional
import com.android.pokemon.data.model.SpriteData

data class Sprite(
    val backDefault: String,
    val backFemale: String,
    val backShiny: String,
    val backShinyFemale: String,
    val frontDefault: String,
    val frontFemale: String,
    val frontShiny: String,
    val frontShinyFemale: String,
)

fun SpriteData?.mapToSprite(): Sprite =
    Sprite(
        backDefault = this?.backDefault.handleOptional(),
        backFemale = this?.backFemale.handleOptional(),
        backShiny = this?.backShiny.handleOptional(),
        backShinyFemale = this?.backShinyFemale.handleOptional(),
        frontDefault = this?.frontDefault.handleOptional(),
        frontFemale = this?.frontFemale.handleOptional(),
        frontShiny = this?.frontShiny.handleOptional(),
        frontShinyFemale = this?.frontShinyFemale.handleOptional()
    )