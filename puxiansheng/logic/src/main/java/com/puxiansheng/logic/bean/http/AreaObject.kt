package com.puxiansheng.logic.bean.http

import android.os.Parcelable
import com.puxiansheng.logic.bean.MenuItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AreaObject(
    val topLevelItem: MenuItem ? = null,

    val secondLevelItem:  MenuItem ? = null

) : Parcelable