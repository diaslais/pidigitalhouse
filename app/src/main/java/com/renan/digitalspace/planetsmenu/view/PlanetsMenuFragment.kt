package com.renan.digitalspace.planetsmenu.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import com.renan.digitalspace.R
import com.renan.digitalspace.planetsmenu.model.Planet
import com.renan.digitalspace.planetsmenu.repository.PlanetRepository
import com.renan.digitalspace.planetsmenu.viewmodel.PlanetViewModel

class PlanetsMenuFragment : Fragment(), IPlanetClick {
    private lateinit var planetsView: View
    private lateinit var _planetViewModel: PlanetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        planetsView = inflater.inflate(R.layout.fragment_planets_menu, container, false)
        return planetsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            PlanetViewModel.PlanetViewModelFactory(PlanetRepository())
        ).get(PlanetViewModel::class.java)

        viewModel.planetsData.observe(viewLifecycleOwner, Observer {
            makePlanetsRecyclerview(it)
        })
        _planetViewModel = viewModel
        viewModel.getPlanets()
    }

    private fun makePlanetsRecyclerview(planets: List<Planet>) {
        val planetsRecyclerView = planetsView.findViewById<RecyclerView>(R.id.planetsRecyclerview)

        planetsRecyclerView.adapter = PlanetsMenuAdapter(planets, this)
        planetsRecyclerView.setHasFixedSize(true)
        planetsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onPlanetClick(planet: Planet, position: Int) {
        _planetViewModel.setPlanet(planet)

        val planetName = planetsView.findViewById<TextView>(R.id.txtPlanetName)
        val planetDescription = planetsView.findViewById<TextView>(R.id.txtPlanetDescription)
        val planetImg = planetsView.findViewById<ImageView>(R.id.imgPlanet)

        val bottomSheet = planetsView.findViewById<MaterialCardView>(R.id.mcvBottomsheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
            val titleBottomSheet = planetsView.findViewById<TextView>(R.id.tvTitleBottomsheet)
            val textBottomSheet = planetsView.findViewById<TextView>(R.id.tvTextBottomsheet)

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
        val bottomSheet = planetsView.findViewById<MaterialCardView>(R.id.mcvBottomsheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        val description = planetsView.findViewById<TextView>(R.id.txtPlanetDescription)

        val btnTechnical = planetsView.findViewById<Button>(R.id.btnTechnicalInformation)
        val btnCuriosities = planetsView.findViewById<Button>(R.id.btnCuriosities)
        val btnNews = planetsView.findViewById<Button>(R.id.btnNews)

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
        bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>,
    ) {
        val titleBottomSheet = planetsView.findViewById<TextView>(R.id.tvTitleBottomsheet)
        val textBottomSheet = planetsView.findViewById<TextView>(R.id.tvTextBottomsheet)

        titleBottomSheet.text = titleString
        textBottomSheet.text = textString

        bottomSheetBehavior.peekHeight = 700
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}