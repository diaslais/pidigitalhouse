package com.nasinha.digitalspace.bibliography.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.planetsmenu.model.Planet

class BibliographyAdapter (private val dataSet: List<Planet>):
    RecyclerView.Adapter<BibliographyAdapter.BibliographyViewHolder>() {

    class BibliographyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        private var txtTitle = itemView.findViewById<TextView>(R.id.txtBibliographyTitle)
        private var txtText = itemView.findViewById<TextView>(R.id.txtBibliographyText)

        fun bind(planet: Planet){
            txtTitle.text = planet.name
            txtText.text = planet.bibliography
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BibliographyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bibliography, parent, false)
        return BibliographyViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: BibliographyViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}