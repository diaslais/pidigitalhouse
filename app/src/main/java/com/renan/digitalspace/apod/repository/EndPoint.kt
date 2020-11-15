package com.renan.digitalspace.apod.repository

import com.renan.digitalspace.apod.model.ApiResponseModelAPOD
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {

    @GET("planetary/apod")
    fun getAstronomicalFact(@Query("api_key") api_key: String = "gAFkqlGusIgjAzp0UrMhjbkqYfzlyq5jRL8BeBjv"): Call<ApiResponseModelAPOD>

}