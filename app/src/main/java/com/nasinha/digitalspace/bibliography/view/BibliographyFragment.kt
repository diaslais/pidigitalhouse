package com.nasinha.digitalspace.bibliography.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nasinha.digitalspace.R
import com.nasinha.digitalspace.planetsmenu.model.Planet
import com.nasinha.digitalspace.planetsmenu.repository.PlanetRepository
import com.nasinha.digitalspace.planetsmenu.viewmodel.PlanetViewModel
import com.nasinha.digitalspace.utils.DrawerUtils.lockDrawer

class BibliographyFragment : Fragment() {
    private lateinit var _biblioView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _biblioView = inflater.inflate(R.layout.fragment_bibliography, container, false)
        return _biblioView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lockDrawer(requireActivity())

        view.findViewById<ImageButton>(R.id.btnBackBibliography).setOnClickListener {
            activity?.onBackPressed()
        }

        val viewModel = ViewModelProvider(
            this,
            PlanetViewModel.PlanetViewModelFactory(PlanetRepository())
        ).get(PlanetViewModel::class.java)

        viewModel.getPlanets()

        viewModel.planetsData.observe(viewLifecycleOwner) {
            makeBibliographyRecyclerview(it)
        }
    }

    private fun makeBibliographyRecyclerview(planets: List<Planet>){
        val biblioRecyclerView = _biblioView.findViewById<RecyclerView>(R.id.bibliographyRecyclerView)

        biblioRecyclerView.adapter = BibliographyAdapter(planets)
        biblioRecyclerView.setHasFixedSize(true)
        biblioRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}