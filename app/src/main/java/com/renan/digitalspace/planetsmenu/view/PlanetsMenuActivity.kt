package com.renan.digitalspace.planetsmenu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet
import com.renan.digitalspace.planetsmenu.repository.PlanetRepository
import com.renan.digitalspace.planetsmenu.viewmodel.PlanetViewModel

class PlanetsMenuActivity : AppCompatActivity(), IPlanetClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets_menu)

        val viewModel = ViewModelProvider(
            this,
            PlanetViewModel.PlanetViewModelFactory(PlanetRepository())
        ).get(PlanetViewModel::class.java)

        viewModel.planetsData.observe(this, Observer {
            makePlanetsRecyclerview(it)
        })
        viewModel.getPlanets()
    }

    private fun makePlanetsRecyclerview(planets: List<Planet>) {
        val recyclerView = findViewById<RecyclerView>(R.id.planetsRecyclerview)
        val viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val viewAdapter = PlanetsMenuAdapter(planets, this)

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }
    }

    override fun onPlanetClick(planet: Planet, position: Int) {
        val planetImg = findViewById<ImageView>(R.id.imgPlanet)
        val planetName = findViewById<TextView>(R.id.txtPlanetName)
        val planetDescription = findViewById<TextView>(R.id.txtPlanetDescription)

        planetImg.setImageResource(planet.image)
        planetName.text = planet.name
        planetDescription.text = planet.description
    }
}