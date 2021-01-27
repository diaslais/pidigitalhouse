package com.nasinha.digitalspace.extension

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.button.MaterialButton
import com.nasinha.digitalspace.utils.ViewUtils.dpToPx


fun MaterialButton.setLoading(loading: Boolean){
    text = ""

    animate().apply {
        startDelay = 100
        duration = 500
//        x(dpToPx(48.0).toFloat())
//        y(dpToPx(58.0).toFloat())
        scaleX(0F)
        start()
    }
}

fun MaterialButton.toggleSize(loading : Boolean){
    val v = this
    val params = v.layoutParams

    if (loading) {
        params.width = dpToPx(48.0).toInt()
        params.height = dpToPx(58.0).toInt()

        val progressDrawable = CircularProgressDrawable(context).apply {
            setStyle(CircularProgressDrawable.LARGE)
            setColorSchemeColors(Color.WHITE)
        }

        v.icon = progressDrawable
        v.setTextColor(0x00FFFFFF.and(v.currentTextColor))
        v.cornerRadius = 300
        v.setPadding(
            dpToPx(24.0).toInt(),
            0,
            0,
            0
        )

        v.isEnabled = false

        progressDrawable.start()
        progressDrawable.callback = object : Drawable.Callback {
            override fun unscheduleDrawable(who: Drawable, what: Runnable) {
            }
            override fun invalidateDrawable(who: Drawable) {
                v.invalidate()
            }
            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
            }
        }
    } else {
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT

        v.icon = null
        v.cornerRadius = dpToPx(8.0).toInt()
        v.setPadding(
            dpToPx(40.0).toInt(),
            0,
            dpToPx(40.0).toInt(),
            0
        )

        v.isEnabled = true

        v.setTextColor(Color.parseColor("#FF000000").or(v.currentTextColor))
    }

    v.layoutParams = params
}