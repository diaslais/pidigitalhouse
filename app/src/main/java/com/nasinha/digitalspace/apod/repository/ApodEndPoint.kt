package com.nasinha.digitalspace.apod.repository

import com.nasinha.digitalspace.apod.network.NetworkUtilsApod
import com.nasinha.digitalspace.apod.model.ApodResponseModel
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface ApodEndPoint {

    @GET("planetary/apod")
    suspend fun getAstronomicalFact(
        @Query("date") date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
            Calendar.getInstance().time
        )
    ): ApodResponseModel

    companion object {
        val endpoint: ApodEndPoint by lazy {
            NetworkUtilsApod.getRetroFitInstance().create(ApodEndPoint::class.java)
        }
    }
}