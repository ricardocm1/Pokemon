package com.android.pokemon.presentation.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.pokemon.R
import com.android.pokemon.databinding.ActivityGalleryBinding
import com.android.pokemon.presentation.gallery.adpter.GalleryAdapter
import com.android.pokemon.presentation.model.PokemonPresentation
import com.android.pokemon.presentation.pokemon.PokemonActivity
import com.android.pokemon.util.EndlessScrollView
import com.android.pokemon.util.GridSpacingItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var galleryAdapter: GalleryAdapter

    private val viewModel: GalleryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()

        viewModel.loadPokemonList()
    }

    private fun setupUI() {
        with(binding) {
            rvPokemonList.run {
                galleryAdapter = GalleryAdapter(mutableListOf(), this@GalleryActivity::onClick)
                adapter = galleryAdapter
                addItemDecoration(
                    GridSpacingItemDecoration(
                        resources.getDimensionPixelSize(
                            R.dimen.margin
                        )
                    )
                )
            }

            nsPokemonList.setOnScrollChangeListener(object : EndlessScrollView() {
                override fun onLoadMore() {
                    viewModel.loadPokemonList()
                }
            })
        }
    }

    private fun setupObservers() {
        viewModel.run {
            pokemonList.observe(this@GalleryActivity, { pokemonList ->
//                galleryAdapter.addItems(pokemonList.mapToPresentation())
                galleryAdapter.addItems(pokemonList)
            })

            detailsLoaded.observe(this@GalleryActivity, { position ->
                galleryAdapter.notifyItemChanged(position)
            })

            isLoading.observe(this@GalleryActivity, { isLoading ->
                if (isLoading) {
                    Toast.makeText(
                        this@GalleryActivity,
                        getString(R.string.loading),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

            showErrorMessage.observe(this@GalleryActivity, { shouldShow ->
                if (shouldShow) {
                    Toast.makeText(
                        this@GalleryActivity,
                        getString(R.string.load_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

            hasReachedEnd.observe(this@GalleryActivity, { hasReachedEnd ->
                if (hasReachedEnd) {
                    Toast.makeText(
                        this@GalleryActivity,
                        getString(R.string.no_more_items_available),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private fun onClick(pokemon: PokemonPresentation) {
        PokemonActivity.start(this, pokemon)
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, GalleryActivity::class.java)
            context.startActivity(intent)
        }
    }
}
