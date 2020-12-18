package com.nasinha.digitalspace.favorite.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import coil.ImageLoader
import coil.request.SuccessResult
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

object FavoriteUtils {

    fun stringToDate(date: String): Date {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.parse(date)
    }

    fun dateModifier(date: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val parsedDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        } else {
            date
        }
    }

    fun checkPermissions(activity: Activity, view: View, image: Bitmap?) {
        var result: Int
        val listPermissionsNeeded: MutableList<String> = ArrayList()

        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        for (p in permissions) {
            result = ContextCompat.checkSelfPermission(view.context, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            activity.let {
                ActivityCompat.requestPermissions(
                    it,
                    listPermissionsNeeded.toTypedArray(),
                    100
                )
            }
        } else {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            share.putExtra(Intent.EXTRA_STREAM, getImageUri(view, image!!))
            startActivity(view.context, Intent.createChooser(share, "Share image via"), Bundle())
        }
    }

    suspend fun getBitmapFromView(view: View, imageUri: String): Bitmap? {
        val loading = ImageLoader(view.context)
        val request = coil.request.ImageRequest.Builder(view.context).data(imageUri).build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    fun getImageUri(view: View, image: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(view.context.contentResolver, image, "title", null)
        return Uri.parse(path)
    }
}