package com.puxiansheng.www.common

import android.widget.ImageView
import com.puxiansheng.uio.GlideApp
import com.puxiansheng.www.R

fun ImageView.url(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.img_pxs_defult_small).placeholder(R.mipmap.img_pxs_defult_small)
        .fallback(R.mipmap.img_pxs_defult_small).into(this)
}

fun ImageView.urlBg(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.img_pxs_defult_big).placeholder(R.mipmap.img_pxs_defult_big)
        .fallback(R.mipmap.img_pxs_defult_big).into(this)
}

fun ImageView.urlIcon(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.ic_default_icon).placeholder(R.mipmap.ic_default_icon)
        .fallback(R.mipmap.ic_default_icon).into(this)
}