package com.puxiansheng.logic.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.puxiansheng.logic.util.GlideApp
import com.bumptech.glide.Priority
import com.zhihu.matisse.engine.ImageEngine

class GlideImageEngine : ImageEngine {
    override fun loadImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        context?.let {
            imageView?.let { it1 ->
                GlideApp.with(it)
                    .load(uri)
                    .override(resizeX, resizeY)
                    .priority(Priority.HIGH)
                    .into(it1)
            }
        }
    }

    override fun loadGifImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        context?.let {
            imageView?.let { it1 ->
                GlideApp.with(it)
                    .asGif()
                    .load(uri)
                    .override(resizeX, resizeY)
                    .priority(Priority.HIGH)
                    .into(it1)
            }
        }
    }

    override fun supportAnimatedGif(): Boolean {
        return true
    }

    override fun loadGifThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        context?.let {
            imageView?.let { it1 ->
                GlideApp.with(it)
                    .asBitmap()
                    .load(uri)
                    .placeholder(placeholder)
                    .override(resize, resize)
                    .centerCrop()
                    .into(it1)
            }
        }
    }

    override fun loadThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        context?.let {
            imageView?.let { it1 ->
                GlideApp.with(it)
                    .asBitmap()
                    .load(uri)
                    .placeholder(placeholder)
                    .override(resize, resize)
                    .centerCrop()
                    .into(it1)
            }
        }
    }
}