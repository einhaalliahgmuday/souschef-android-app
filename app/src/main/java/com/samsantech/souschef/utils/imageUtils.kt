package com.samsantech.souschef.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

fun convertUriToBitmap(context: Context, uri: Uri): Bitmap? {
    val bitmap: Bitmap?

    if (Build.VERSION.SDK_INT < 28) {
        bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(context.contentResolver, uri)
        bitmap = ImageDecoder.decodeBitmap(source)
    }

    return bitmap
}