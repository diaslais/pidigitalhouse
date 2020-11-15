package com.renan.digitalspace.epic.repository

import com.renan.digitalspace.epic.model.ApiResponseModelEPIC
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointEpic {

    @GET("natural/images")
    fun getImageDay(@Query("api_key") api_key: String = "gAFkqlGusIgjAzp0UrMhjbkqYfzlyq5jRL8BeBjv"): Call<List<ApiResponseModelEPIC>>

}