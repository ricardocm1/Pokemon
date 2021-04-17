package com.android.pokemon.presentation.model

import com.android.pokemon.domain.model.PokemonDetail
import java.io.Serializable

data class PokemonPresentation(
    val id: Int,
    val name: String,
    var image: String = "",
    var height: String = "",
    var weight: String = "",
    var types: String = "",
    var moves: String = "",
    var abilities: String = "",
    var forms: String = "",
    var images: List<String> = listOf()
) : Serializable {
    fun setDetails(details: PokemonDetail) {
        this.image = details.sprites.frontDefault
        this.height = details.height.toString()
        this.weight = details.weight.toString()
        this.types = details.types.joinToString(", ") { it.type.name }
        this.moves = details.moves.joinToString(", ") { it.move.name }
        this.abilities = details.abilities.joinToString(", ") { it.ability.name }
        this.forms = details.forms.joinToString(", ") { it.name }

        details.sprites.also { sprite ->
            this.images = listOf(
                sprite.backDefault,
                sprite.backFemale,
                sprite.backShiny,
                sprite.backShinyFemale,
                sprite.frontDefault,
                sprite.frontFemale,
                sprite.frontShinyFemale,
                sprite.frontShiny
            )
        }
    }
}

//fun PokemonDetail.mapToPokemonPresentatiton(): PokemonPresentation =
//    PokemonPresentation(
//        name = this.name.handleOptional(),
//        image = this.sprites.frontDefault.handleOptional()
//    )
//
//fun List<PokemonDetail>?.mapToPresentation(): List<PokemonPresentation> =
//    this?.map { it.mapToPokemonPresentatiton() }.handleOptional()