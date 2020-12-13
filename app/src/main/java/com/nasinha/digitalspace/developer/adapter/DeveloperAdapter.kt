package com.nasinha.digitalspace.developer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.developer.entity.DeveloperEntity

class DeveloperAdapter(
    private val _developers: MutableList<DeveloperEntity>,
    private val _iDeveloper: IDeveloper
) : RecyclerView.Adapter<DeveloperViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.developer_list_item, parent, false)
        return DeveloperViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeveloperViewHolder, position: Int) {
        val item = _developers[position]

        val image = item.image
        val name = item.name
        val bio = item.bio
        val linkedin = item.linkedin
        val github = item.github

        holder.bind(image, name, bio)

        holder.githubBtn.setOnClickListener {
            _iDeveloper.githubDeveloper(github)
        }

        holder.linkedinBtn.setOnClickListener {
            _iDeveloper.linkedinDeveloper(linkedin)
        }
    }

    override fun getItemCount() = _developers.size


}