package com.renan.digitalspace.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.favorite.entity.FavoriteEntity
import com.renan.digitalspace.favorite.model.FavoriteModel

class FavoriteAdapter(
    private val _favorites: MutableList<FavoriteEntity>,
    private val listener: (FavoriteEntity) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    fun addFavorite(favorite: FavoriteEntity) {
        _favorites.add(favorite)
        notifyDataSetChanged()
    }

    fun addFavorites(favorites: List<FavoriteEntity>) {
        _favorites.addAll(favorites)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = _favorites[position]

        val image = item.image
        val title = item.title
        val date = item.date

        holder.bind(image, title, date)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = _favorites.size
    fun deleteAll() {
        _favorites.clear()
        notifyDataSetChanged()
    }
}