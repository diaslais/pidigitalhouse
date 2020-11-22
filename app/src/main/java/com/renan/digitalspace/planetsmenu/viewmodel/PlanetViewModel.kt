package com.renan.digitalspace.planetsmenu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renan.digitalspace.planetsmenu.model.Planet
import com.renan.digitalspace.planetsmenu.repository.PlanetRepository

class PlanetViewModel(private val repository: PlanetRepository) : ViewModel() {
    val planetsData = MutableLiveData<List<Planet>>()

    //    var selectedPlanet = repository.getPlanets { planetsData.value?.get(0) } as Planet
    lateinit var selectedPlanet: Planet

    fun getPlanets() {
        repository.getPlanets {
            planetsData.value = it
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