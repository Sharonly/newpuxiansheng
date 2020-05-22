package com.puxiansheng.util.ext

import android.content.Context
import android.widget.Toast
import java.security.MessageDigest

fun String.md5(salt: String?): String {
    val saltedString = salt?.let { this + it } ?: this
    val md5MessageDigest = MessageDigest.getInstance("MD5")
    val digest = md5MessageDigest.digest(saltedString.toByteArray())
    val stringBuilder = StringBuilder()
    for (byte in digest) {
        val value = byte.toInt() and 0xff
        var hexString = Integer.toHexString(value)
        if (hexString.length < 2) {
            hexString = "0$hexString"
        }

        stringBuilder.append(hexString)
    }
    return stringBuilder.toString()
}

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}