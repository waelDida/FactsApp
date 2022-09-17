package com.wapp.factsapp.uploadhelper

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class PhotoUploader @Inject constructor(@ApplicationContext private val context: Context) {

    fun getByteArray(uri: Uri?):ByteArray{
        val bmp = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        val boas = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 25, boas)
        return boas.toByteArray()
    }
}