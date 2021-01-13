package com.nasinha.digitalspace.apod.utils

object ApodUtils {
     fun getIdVideo(urlVideo: String): String {
        val index = urlVideo.indexOf("=")
        val url = urlVideo.subSequence(index + 1, urlVideo.length)
        val idVideo = url.subSequence(0, 11)
        return idVideo.toString()
    }
}