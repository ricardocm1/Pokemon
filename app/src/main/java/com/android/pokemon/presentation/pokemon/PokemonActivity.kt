package com.android.pokemon.presentation.pokemon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.pokemon.databinding.ActivityPokemonBinding
import com.android.pokemon.presentation.model.PokemonPresentation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI(intent.getSerializableExtra(POKEMON_EXTRA) as PokemonPresentation)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupUI(pokemon: PokemonPresentation) {
        with(binding) {
            setSupportActionBar(tbToolbar.toolbar) //TODO
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayShowTitleEnabled(false)
            }

            ttPokemon.text = pokemon.name

            Glide.with(this@PokemonActivity)
                .load(pokemon.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivPokemon)

            tvPokemonHeightValue.text = pokemon.height
            tvPokemonWeightValue.text = pokemon.weight
            tvPokemonTypesValue.text = pokemon.types
            tvPokemonMovesValue.text = pokemon.moves
            tvPokemonAbilitiesValue.text = pokemon.abilities
            tvPokemonFormsValue.text = pokemon.forms
        }
    }

    companion object {
        private const val POKEMON_EXTRA = "POKEMON_EXTRA"

        fun start(context: Context, pokemon: PokemonPresentation) {
            val intent = Intent(context, PokemonActivity::class.java).apply {
                putExtra(POKEMON_EXTRA, pokemon)
            }

            context.startActivity(intent)
        }
    }

}
