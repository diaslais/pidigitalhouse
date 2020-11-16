package com.renan.digitalspace.epic.repository

import com.renan.digitalspace.epic.model.ApiResponseModelEPIC
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPointEpic {

    @GET("natural")
    fun getImageDay(): Call<List<ApiResponseModelEPIC>>

    @GET("natural/available")
    fun getLastDAy(): Call<List<String>>

    @GET("archive/natural/{date}/png/{imageId}.png")
    fun getImageDay(
        @Path("date") date: String,
        @Path("imageId") image: String

    ): Call<String>

}