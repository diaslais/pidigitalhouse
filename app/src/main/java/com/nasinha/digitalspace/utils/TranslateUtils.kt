package com.nasinha.digitalspace.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslatorOptions
import com.nasinha.digitalspace.utils.Constants.APP_KEY
import com.nasinha.digitalspace.utils.Constants.SWITCH_PREFS
import java.util.prefs.Preferences

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

    fun getCheckPrefs(context: Context): Boolean {
        val preferences: SharedPreferences =
            context.getSharedPreferences(APP_KEY, AppCompatActivity.MODE_PRIVATE)
        return preferences.getBoolean(SWITCH_PREFS, false)
    }

    fun saveCheckPrefs(context: Context, status: Boolean) {
        val preferences: SharedPreferences =
            context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE)
        preferences.edit()?.putBoolean(SWITCH_PREFS, status)?.apply()
    }
}