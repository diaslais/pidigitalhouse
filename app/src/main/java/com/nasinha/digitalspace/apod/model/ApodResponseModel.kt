package com.nasinha.digitalspace.apod.model

data class ApodResponseModel(

    val date: String,
    val explanation: String,
    val title: String,
    val url: String,
    val media_type: String

)