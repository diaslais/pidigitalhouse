package com.nasinha.digitalspace.developer.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.developer_list_item.view.*

class DeveloperViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val _imageView = view.findViewById<ImageView>(R.id.ivThumbnailDeveloper)
    private val _nameView = view.findViewById<TextView>(R.id.tvNameDeveloper)
    private val _bioView = view.findViewById<TextView>(R.id.tvBioDeveloper)
    val linkedinBtn = view.findViewById<ImageButton>(R.id.ibLinkedinDeveloper)
    val githubBtn = view.findViewById<ImageButton>(R.id.ibGithubDeveloper)

    fun bind(thumbnail: String, name: String, bio: String) {
        _nameView.text = name
        _bioView.text = bio
        Picasso.get().load(thumbnail).into(_imageView)
    }
}