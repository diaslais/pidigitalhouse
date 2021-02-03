package com.nasinha.digitalspace.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.nasinha.digitalspace.BuildConfig
import com.nasinha.digitalspace.utils.Constants.COMPARTILHAR
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

object FavoriteUtils {

    fun stringToDate(date: String): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.parse(date)
    }

    fun dateModifier(date: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localeBr = Locale("pt", "BR")
            val parsedDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            parsedDate.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(localeBr)
            )
        } else {
            date
        }
    }

    fun shareImageText(
        activity: Activity,
        view: View,
        imageView: ImageView,
        text: String?
    ) {
        val bitmap = getBitmapFromView(imageView)

        try {
            val imagePath = File(activity.cacheDir, "images")
            val newFile = File(imagePath, "image.png")

            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(newFile))

            val uri = FileProvider.getUriForFile(
                activity,
                BuildConfig.APPLICATION_ID + ".fileprovider",
                newFile
            )

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)

            if (text.isNullOrEmpty()) {
                shareIntent.type = "image/PNG"
                startActivity(
                    view.context,
                    Intent.createChooser(shareIntent, COMPARTILHAR),
                    Bundle()
                )
            } else {
                shareIntent.putExtra(Intent.EXTRA_TEXT, text)
                shareIntent.type = "*/*"
                startActivity(
                    view.context,
                    Intent.createChooser(shareIntent, COMPARTILHAR),
                    Bundle()
                )
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun shareVideo(
        view: View,
        text: String
    ) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.type = "text/html"
        startActivity(
            view.context,
            Intent.createChooser(shareIntent, COMPARTILHAR), Bundle()
        )
    }

    fun getBitmapFromView(view: ImageView): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}