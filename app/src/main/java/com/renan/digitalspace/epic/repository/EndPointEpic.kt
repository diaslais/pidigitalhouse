package com.renan.digitalspace.epic.repository

import android.net.Uri
import com.renan.digitalspace.epic.model.ApiResponseModelEPIC
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface EndPointEpic {

    @GET("api/natural")
    fun getImageDay(): Call<List<ApiResponseModelEPIC>>

    @GET("api/natural/available")
    fun getLastDAy(): Call<List<String>>


}