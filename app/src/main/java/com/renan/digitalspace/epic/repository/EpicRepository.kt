package com.renan.digitalspace.epic.repository

class EpicRepository {
    private val request = EpicEndPoint.endpoint

    suspend fun getImageDay() = request.getImageDay()
    suspend fun getLastDay() = request.getLastDAy()
}