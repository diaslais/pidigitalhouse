package com.nasinha.digitalspace.planetsmenu.view

import com.nasinha.digitalspace.planetsmenu.model.Planet

interface IPlanetClick {
    fun onPlanetClick(planet: Planet, position: Int)
}