package com.nasinha.digitalspace.utils

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
import com.nasinha.digitalspace.utils.Constants.COMPARTILHAR
import com.nasinha.digitalspace.utils.Constants.TITLE
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
            val localeBr = Locale("pt", "BR")
            val parsedDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            parsedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(localeBr))
        } else {
            date
        }
    }

    fun shareImageText(activity: Activity, view: View, image: Bitmap?, text: String?) {

        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val listPermissionsNeeded: MutableList<String> = permissionCheck(view, permissions)

        if (listPermissionsNeeded.isNotEmpty()) {
            permissionResquest(activity, listPermissionsNeeded)
        } else {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_STREAM, getImageUri(activity, view, image!!))
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
        }
    }

    private fun permissionCheck(view: View, permissions: Array<String>): MutableList<String> {
        val listPermissionsNeeded: MutableList<String> = ArrayList()

        for (p in permissions) {
            val result = ContextCompat.checkSelfPermission(view.context, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }
        return listPermissionsNeeded
    }

    private fun permissionResquest(activity: Activity, listPermissionsNeeded: MutableList<String>) {
        activity.let {
            ActivityCompat.requestPermissions(
                it,
                listPermissionsNeeded.toTypedArray(),
                100
            )
        }
    }

    suspend fun getBitmapFromView(view: View, imageUri: String): Bitmap? {
        val loading = ImageLoader(view.context)
        val request = coil.request.ImageRequest.Builder(view.context).data(imageUri).build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    fun getImageUri(activity: Activity, view: View, imageBitmap: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()

        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)

        val path =
            MediaStore.Images.Media.insertImage(
                view.context.contentResolver,
                imageBitmap,
                TITLE,
                null
            )

        return Uri.parse(path)
    }
}