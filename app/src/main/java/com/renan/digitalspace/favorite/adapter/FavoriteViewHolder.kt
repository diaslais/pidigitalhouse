package com.renan.digitalspace.favorite.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.squareup.picasso.Picasso

class FavoriteViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val _imageView = view.findViewById<ImageView>(R.id.ivThumbnailFavoriteList)
    private val _titleView = view.findViewById<TextView>(R.id.tvTitleFavoriteList)
    private val _dateView = view.findViewById<TextView>(R.id.tvDateFavoriteList)

    fun bind(thumbnail: String, title: String, date: String) {
        _titleView.text = title
        _dateView.text = date

        Picasso.get().load(thumbnail).into(_imageView)
    }
}