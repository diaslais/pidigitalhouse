package com.renan.digitalspace.apod.model

data class ApodResponseModel(
    val copyright: String?,
    val date: String,
    val explanation:String,
    val title:String,
    val url:String

)