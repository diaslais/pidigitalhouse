package com.renan.digitalspace.planetsmenu.view

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet

class PlanetsMenuViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private var planetImage = view.findViewById<ImageView>(R.id.imgPlanetRecycler)

    fun bind (planet: Planet, action: IPlanetClick) {
        planetImage.setImageResource(planet.image)

        itemView.setOnClickListener {
            action.onPlanetClick(planet, adapterPosition)
        }
    }
}