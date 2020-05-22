package com.puxiansheng.util.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

fun File.asBitmap() : Bitmap? {
    return BitmapFactory.decodeFile(absolutePath)
}

fun File.writeToTempStorage() {

}