package com.nasinha.digitalspace.epic.repository

import com.nasinha.digitalspace.epic.data.NetworkUtilsEpic
import com.nasinha.digitalspace.epic.model.EpicResponseModel
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