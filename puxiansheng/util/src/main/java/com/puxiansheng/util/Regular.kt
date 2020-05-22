package com.puxiansheng.util

import java.util.regex.Pattern

object Regular {
    fun isUserName(str: String?): Boolean =
        str?.matches("^(?=.*[A-Za-z])[A-Za-z0-9\\d]{4,20}\$".toRegex()) ?: false

    fun isPassword(str: String?): Boolean =
        str?.matches("^[A-Za-z0-9]{6,20}\$".toRegex()) ?: false

    fun isPhoneNumber(str: String?): Boolean =
        str?.matches("^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}\$".toRegex())
            ?: false

    fun isEmail(str: String?): Boolean =
        str?.matches("^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+\$".toRegex())
            ?: false
}