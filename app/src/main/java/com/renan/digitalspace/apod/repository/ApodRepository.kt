package com.renan.digitalspace.apod.repository

class ApodRepository {
    private val request = ApodEndPoint.endpoint

    suspend fun getData() = request.getAstronomicalFact()
}