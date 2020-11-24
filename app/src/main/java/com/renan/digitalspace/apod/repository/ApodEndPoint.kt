package com.renan.digitalspace.apod.repository

import com.renan.digitalspace.apod.data.NetworkUtilsApod
import com.renan.digitalspace.apod.model.ApodResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodEndPoint {

    @GET("planetary/apod")
   suspend fun getAstronomicalFact(@Query("api_key") api_key: String = "gAFkqlGusIgjAzp0UrMhjbkqYfzlyq5jRL8BeBjv"): ApodResponseModel

    companion object{
        val endpoint: ApodEndPoint by lazy{
            NetworkUtilsApod.getRetroFitInstance().create(ApodEndPoint::class.java)
        }
    }
}