package com.renan.digitalspace.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.favorite.model.FavoriteModel

class FavoriteAdapter(
    private val favorites: List<FavoriteModel>,
    private val listener: (FavoriteModel) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = favorites[position]

        val image = item.image
        val title = item.title
        val date = item.date

        holder.bind(image, title, date)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = favorites.size

}