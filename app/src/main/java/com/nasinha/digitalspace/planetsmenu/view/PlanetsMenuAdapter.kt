package com.nasinha.digitalspace.planetsmenu.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.planetsmenu.model.Planet

class PlanetsMenuAdapter (
    private val context: Context,
    private var dataSet: List<Planet>,
    private val clickListener: IPlanetClick
): RecyclerView.Adapter<PlanetsMenuAdapter.PlanetsMenuViewHolder>(){
    lateinit var view: View
    var checkedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsMenuViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_planets_menu, parent, false)
        return PlanetsMenuViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: PlanetsMenuViewHolder, position: Int) {
        holder.planetImage.setImageResource(dataSet[position].image)

        if (checkedPosition == position) {
            holder.planetImage.background = ContextCompat.getDrawable(
                context,
                R.drawable.selected_planet
            )
        } else {
            holder.planetImage.background = ContextCompat.getDrawable(
                context,
                R.drawable.default_planet
            )
        }

        holder.itemView.setOnClickListener{
            holder.planetImage.background = ContextCompat.getDrawable(
                context,
                R.drawable.selected_planet
            )
            if (checkedPosition != position) {
                notifyItemChanged(checkedPosition)
                checkedPosition = position
            }
            clickListener.onPlanetClick(dataSet[position], position)
        }
    }

        class PlanetsMenuViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val planetImage: ImageView

            init {
                planetImage = itemView.findViewById(R.id.imgPlanetRecycler)
            }
        }
}




