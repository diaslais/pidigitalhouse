package com.nasinha.digitalspace.apod.model

import com.google.gson.annotations.SerializedName

data class ApodResponseModel(
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("media_type")
    val media_type: String

)