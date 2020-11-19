package com.renan.digitalspace.planetsmenu.view

import com.renan.digitalspace.planetsmenu.model.Planet

interface IPlanetClick {
    fun onPlanetClick(planet: Planet, position: Int)
}