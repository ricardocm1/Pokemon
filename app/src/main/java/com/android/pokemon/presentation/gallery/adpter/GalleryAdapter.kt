package com.android.pokemon.presentation.gallery.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pokemon.databinding.CardPhotoBinding
import com.android.pokemon.presentation.model.PokemonPresentation
import com.android.pokemon.presentation.gallery.viewholder.GalleryViewHolder

class GalleryAdapter(
    private val list: MutableList<PokemonPresentation>,
    private val onClick: ((photo: PokemonPresentation) -> Unit)
) : RecyclerView.Adapter<GalleryViewHolder>() {

    private var lastPosition = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = CardPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding, onClick)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        list[position].run {
            holder.populate(this)
        }
    }

    fun addItems(photosToAdd: List<PokemonPresentation>) {
        list.addAll(photosToAdd)
        notifyItemRangeInserted(lastPosition, photosToAdd.size)
        lastPosition = list.size
    }

}
