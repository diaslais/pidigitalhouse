package com.renan.digitalspace.planetsmenu.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet

class PlanetsMenuAdapter (
    private val dataSet: List<Planet>,
    val clickListener: IPlanetClick
): RecyclerView.Adapter<PlanetsMenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planets_menu, parent, false)
        return PlanetsMenuViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: PlanetsMenuViewHolder, position: Int) {
        holder.bind(dataSet[position], clickListener)
    }
}