package com.puxiansheng.www.common

import android.graphics.drawable.Drawable
import android.widget.TextView
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.puxiansheng.logic.util.GlideApp

fun TextView.drawableTop(url: String) {
    GlideApp.with(this).load(url)
        .into(object : CustomTarget<Drawable>(90, 90) {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) = setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, resource, null, null
            )

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}