package com.nasinha.digitalspace.apod.repository

class ApodRepository {
    private val request = ApodEndPoint.endpoint

    suspend fun getData() = request.getAstronomicalFact()
}