package com.renan.digitalspace.epic.repository

import com.renan.digitalspace.epic.data.NetworkUtilsEpic
import com.renan.digitalspace.epic.model.EpicResponseModel
import retrofit2.http.GET

interface EpicEndPoint {

    @GET("api/natural")
    suspend fun getImageDay(): List<EpicResponseModel>

    @GET("api/natural/available")
    suspend fun getLastDAy(): List<String>

    companion object {
        val endpoint: EpicEndPoint by lazy {
            NetworkUtilsEpic.getRetroFitInstance().create(EpicEndPoint::class.java)
        }
    }


}