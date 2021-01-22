package com.nasinha.digitalspace.utils

import android.content.res.Resources
import android.util.TypedValue

object ViewUtils {

    fun dpToPx(dp : Double) : Double {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            Resources.getSystem().displayMetrics
        ).toDouble()
    }
}