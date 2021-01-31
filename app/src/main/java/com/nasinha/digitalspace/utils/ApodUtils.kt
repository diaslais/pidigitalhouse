package com.nasinha.digitalspace.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object ApodUtils {
    fun getIdVideo(urlVideo: String): String {

        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(urlVideo)
        return if (matcher.find()) {
            matcher.group()
        } else {
            "error"
        }
    }
}