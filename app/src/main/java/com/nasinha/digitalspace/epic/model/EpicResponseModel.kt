package com.nasinha.digitalspace.epic.model

import com.google.gson.annotations.SerializedName

data class EpicResponseModel (
    @SerializedName("caption")
    val caption:String,
    @SerializedName("image")
    val image:String
)