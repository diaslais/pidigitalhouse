package com.renan.digitalspace.repository

import com.renan.digitalspace.model.ApiResponseModelAPOD
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPoint {

    @GET("planetary/apod")
    fun getAstronomicalFact(@Query("api_key") api_key: String = "gAFkqlGusIgjAzp0UrMhjbkqYfzlyq5jRL8BeBjv"): Call<ApiResponseModelAPOD>

}