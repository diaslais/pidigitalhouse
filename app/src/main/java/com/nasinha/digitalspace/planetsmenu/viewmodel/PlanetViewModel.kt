package com.nasinha.digitalspace.planetsmenu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasinha.digitalspace.planetsmenu.model.Planet
import com.nasinha.digitalspace.planetsmenu.repository.PlanetRepository

class PlanetViewModel(private val repository: PlanetRepository) : ViewModel() {
    val planetsData = MutableLiveData<List<Planet>>()

    lateinit var selectedPlanet: Planet

    fun getPlanets() {
        repository.getPlanets {
            planetsData.value = it
            selectedPlanet = it[0]
        }
    }

    fun setPlanet(planet: Planet) {
        selectedPlanet = planet
    }

    class PlanetViewModelFactory(private val repository: PlanetRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PlanetViewModel(repository) as T
        }
    }
}