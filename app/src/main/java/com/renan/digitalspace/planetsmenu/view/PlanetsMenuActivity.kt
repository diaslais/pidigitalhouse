package com.renan.digitalspace.planetsmenu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet
import com.renan.digitalspace.planetsmenu.repository.PlanetRepository
import com.renan.digitalspace.planetsmenu.viewmodel.PlanetViewModel
import kotlinx.android.synthetic.main.activity_planets_menu.*

class PlanetsMenuActivity : AppCompatActivity(), IPlanetClick {
    private lateinit var _planetViewModel: PlanetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planets_menu)

        val viewModel = ViewModelProvider(
            this,
            PlanetViewModel.PlanetViewModelFactory(PlanetRepository())
        ).get(PlanetViewModel::class.java)

        viewModel.planetsData.observe(this, Observer {
            makePlanetsRecyclerview(it)
            descriptionCard(it[0])
        })

        _planetViewModel = viewModel
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
        _planetViewModel.setPlanet(planet)

        val planetName = findViewById<TextView>(R.id.txtPlanetName)
        val planetDescription = findViewById<TextView>(R.id.txtPlanetDescription)
        val planetImg = findViewById<ImageView>(R.id.imgPlanet)

        val bottomSheet = findViewById<MaterialCardView>(R.id.mcvBottomsheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            val titleBottomSheet = findViewById<TextView>(R.id.tvTitleBottomsheet)
            val textBottomSheet = findViewById<TextView>(R.id.tvTextBottomsheet)

            when (titleBottomSheet.text.toString()) {
                getString(R.string.curiosidades) -> textBottomSheet.text = planet.curiosities
                getString(R.string.atualidades) -> textBottomSheet.text = planet.news
                getString(R.string.informa_es_nt_cnicas) -> textBottomSheet.text = planet.technicalInformation
            }
        }

        val selectedPlanet = _planetViewModel.selectedPlanet

        planetImg.setImageResource(selectedPlanet.image)
        planetName.text = selectedPlanet.name
        planetDescription.text = selectedPlanet.description
        descriptionCard(selectedPlanet)
    }

    private fun descriptionCard(planet: Planet) {
        val bottomSheet = findViewById<MaterialCardView>(R.id.mcvBottomsheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val description = findViewById<TextView>(R.id.txtPlanetDescription)

        val btnTechnical = findViewById<Button>(R.id.btnTechnicalInformation)
        val btnCuriosities = findViewById<Button>(R.id.btnCuriosities)
        val btnNews = findViewById<Button>(R.id.btnNews)

        description.text = planet.description

        btnTechnical.setOnClickListener {
            changeTextBottomSheet(
                getString(R.string.informa_es_nt_cnicas),
                planet.technicalInformation,
                bottomSheetBehavior
            )
        }
        btnCuriosities.setOnClickListener {
            changeTextBottomSheet(
                getString(R.string.curiosidades),
                planet.curiosities,
                bottomSheetBehavior
            )
        }
        btnNews.setOnClickListener {
            changeTextBottomSheet(
                getString(R.string.atualidades),
                planet.news,
                bottomSheetBehavior
            )
        }
    }

    private fun changeTextBottomSheet(
        titleString: String,
        textString: String,
        bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    ) {
        val titleBottomSheet = findViewById<TextView>(R.id.tvTitleBottomsheet)
        val textBottomSheet = findViewById<TextView>(R.id.tvTextBottomsheet)

        titleBottomSheet.text = titleString
        textBottomSheet.text = textString

        bottomSheetBehavior.peekHeight = 700
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}