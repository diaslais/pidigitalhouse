package com.renan.digitalspace.favorite.utils

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object FavoriteUtils {
    fun dateModifier(date: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val parsedDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        } else {
            date
        }
    }
}