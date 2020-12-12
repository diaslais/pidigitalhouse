package com.nasinha.digitalspace.apod.model

data class ApodResponseModel(
    val copyright: String?,
    val date: String,
    val explanation:String,
    val title:String,
    val url:String

)