package com.nasinha.digitalspace.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.entity.FavoriteEntity

class FavoriteAdapter(
    private val _favorites: MutableList<FavoriteEntity>,
    private var _iFavorite: IFavorite,
    private val _translateChecked: Boolean,
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
        val title = if (_translateChecked && !item.titleBr.isNullOrEmpty()) item.titleBr else item.title
        val date = item.date
        val type = item.type

        holder.bind(image, title, date, type)
        holder.itemView.setOnClickListener { listener(item) }

        holder.favoriteBtn.setOnClickListener {

            _iFavorite.iFavoriteDelete(
                holder.adapterPosition,
                _favorites[holder.adapterPosition],
                holder.cardView
            )
        }
        holder.shareBtn.setOnClickListener {
            _iFavorite.iFavoriteShare(
                _favorites[holder.adapterPosition],
                holder.imageView
            )
        }
    }

    override fun getItemCount() = _favorites.size
}