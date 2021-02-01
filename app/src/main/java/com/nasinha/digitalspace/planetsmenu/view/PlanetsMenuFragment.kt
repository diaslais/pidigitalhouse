package com.nasinha.digitalspace.planetsmenu.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.planetsmenu.model.Planet
import com.nasinha.digitalspace.planetsmenu.repository.PlanetRepository
import com.nasinha.digitalspace.planetsmenu.viewmodel.PlanetViewModel


class PlanetsMenuFragment : Fragment(), IPlanetClick {
    private lateinit var _planetsView: View
    private lateinit var _planetViewModel: PlanetViewModel
    private lateinit var _navController: NavController
    private lateinit var _selectedPlanet: Planet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _planetsView = inflater.inflate(R.layout.fragment_planets_menu, container, false)
        return _planetsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(
            this,
            PlanetViewModel.PlanetViewModelFactory(PlanetRepository())
        ).get(PlanetViewModel::class.java)

        _planetViewModel = viewModel

        viewModel.planetsData.observe(viewLifecycleOwner) {
            makePlanetsRecyclerview(it)
        }

        viewModel.getPlanets()

        _navController = Navigation.findNavController(view)

        val backButton = view.findViewById<ImageButton>(R.id.btnBackPlanetsMenu)
        backButton.setOnClickListener {
            activity?.onBackPressed()
        }

        _selectedPlanet = _planetViewModel.selectedPlanet
        descriptionCard(_selectedPlanet)
    }

    private fun makePlanetsRecyclerview(planets: List<Planet>) {
        val planetsRecyclerView = _planetsView.findViewById<RecyclerView>(R.id.planetsRecyclerview)

        planetsRecyclerView.adapter = PlanetsMenuAdapter(requireContext(), planets, this)

        planetsRecyclerView.setHasFixedSize(true)
        planetsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onPlanetClick(planet: Planet, position: Int) {
        _planetViewModel.setPlanet(planet)
        _selectedPlanet = _planetViewModel.selectedPlanet

        val planetName = _planetsView.findViewById<TextView>(R.id.txtPlanetName)
        val planetDescription = _planetsView.findViewById<TextView>(R.id.txtPlanetDescription)
        val planetImg = _planetsView.findViewById<ImageView>(R.id.imgPlanet)

        planetImg.setImageResource(_selectedPlanet.image)
        planetName.text = _selectedPlanet.name
        planetDescription.text = _selectedPlanet.description

        descriptionCard(_selectedPlanet)
    }

    private fun descriptionCard(planet: Planet) {
        val planetDescription = _planetsView.findViewById<TextView>(R.id.txtPlanetDescription)
        planetDescription.text = _selectedPlanet.description

        navigateToBottomSheet(
            R.id.btnTechnicalInformation,
            planet.technicalInformation,
            getString(R.string.informacoes_tecnicas)
        )
        navigateToBottomSheet(
            R.id.btnCuriosities,
            planet.curiosities,
            getString(R.string.curiosidades)
        )
        navigateToBottomSheet(
            R.id.btnNews,
            planet.news,
            getString(R.string.atualidades)
        )
    }

    private fun navigateToBottomSheet(
        btnId: Int,
        text: String,
        title: String
    ) {
        val planeta = _planetsView.findViewById<MaterialButton>(btnId)
        planeta.setOnClickListener {
            planeta.isEnabled = false

            val bundle = bundleOf("text" to text, "title" to title)

            Handler(Looper.getMainLooper()).postDelayed({
                planeta.isEnabled = true
            }, 1000)

            _navController.navigate(
                R.id.action_planetsMenuFragment_to_bottomsheetFragment,
                bundle
            )
        }
    }

}