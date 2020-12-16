package com.nasinha.digitalspace.favorite.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.favorite.utils.FavoriteUtils
import com.squareup.picasso.Picasso

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val _imageView = view.findViewById<ImageView>(R.id.ivThumbnailFavoriteItem)
    private val _titleView = view.findViewById<TextView>(R.id.tvTitleFavoriteItem)
    private val _dateView = view.findViewById<TextView>(R.id.tvDateFavoriteItem)
    val favoriteBtn = view.findViewById<ImageButton>(R.id.ibDeleteFavoriteItem)
    val cardView = view.findViewById<MaterialCardView>(R.id.mcvCardLayoutFavoriteList)
    val shareBtn = view.findViewById<ImageButton>(R.id.ibShareFavoriteItem)

    fun bind(thumbnail: String, title: String?, date: String) {
        _titleView.text = if (title.isNullOrEmpty()) "" else title
        _dateView.text = FavoriteUtils.dateModifier(date)

        Picasso.get().load(thumbnail).into(_imageView)
    }
}