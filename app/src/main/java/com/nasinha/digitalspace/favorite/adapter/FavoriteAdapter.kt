package com.nasinha.digitalspace.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

class FavoriteAdapter(
    private val _favorites: MutableList<FavoriteEntity>,
    private var _iFavorite: IFavorite,
    private val listener: (FavoriteEntity) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

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

        holder.favoriteBtn.setOnClickListener {

//            holder.cardView.visibility = View.GONE
            _iFavorite.deleteFavorite(
                holder.adapterPosition,
                _favorites[holder.adapterPosition],
                holder.cardView
            )
        }
        holder.shareBtn.setOnClickListener {
            _iFavorite.shareFavorite(_favorites[holder.adapterPosition])
        }
    }

    override fun getItemCount() = _favorites.size
}