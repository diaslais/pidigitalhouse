package com.nasinha.digitalspace.utils

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslatorOptions

object TranslateUtils {

    fun options(): TranslatorOptions {
        return TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.PORTUGUESE)
            .build()
    }

    fun conditions(): DownloadConditions {
        return DownloadConditions.Builder()
            .requireWifi()
            .build()
    }
}