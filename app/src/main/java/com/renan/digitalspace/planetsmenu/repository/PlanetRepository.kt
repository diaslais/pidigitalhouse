package com.renan.digitalspace.planetsmenu.repository

import com.renan.digitalspace.planetsmenu.model.Planet

class PlanetRepository {

    fun getPlanets(callback: (planets: List<Planet>) -> Unit) {
        val planetsDataSet = setPlanetsList()
        callback.invoke(planetsDataSet)
    }

    private fun setPlanetsList(): List<Planet> {
        return listOf(
            Planet("Sol"),
            Planet("Mercúrio"),
            Planet("Vênus"),
            Planet("Terra")
        )
    }
}