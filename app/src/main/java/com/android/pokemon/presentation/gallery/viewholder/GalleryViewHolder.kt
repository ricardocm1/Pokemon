package com.android.pokemon.presentation.gallery.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.databinding.CardPhotoBinding
import com.android.pokemon.presentation.model.PokemonPresentation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

class GalleryViewHolder(
    private val binding: CardPhotoBinding,
    private val onClick: (photo: PokemonPresentation) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun populate(pokemon: PokemonPresentation) {
        with(binding) {
            ivPhoto.contentDescription = pokemon.name
            tvPokemonName.text = pokemon.name

            Glide.with(root.context)
                .load(pokemon.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(ColorDrawable(Color.WHITE))
                .error(ColorDrawable(Color.GRAY))
                .transition(withCrossFade())
                .dontAnimate()
                .into(ivPhoto)

            root.setOnClickListener {
                onClick(pokemon)
            }
        }
    }

}