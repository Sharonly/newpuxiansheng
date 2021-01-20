package com.puxiansheng.www.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.R
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ShareUtils{

    companion object {

        fun compressScale(context:Context,image: Bitmap): Bitmap? {
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 60, baos)
            // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            Log.d("shopImg", " size = " + baos.toByteArray().size / 1024)
            var options = 90
            while (baos.toByteArray().size / 1024 > 110) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset() // 重置baos即清空baos
                image.compress(
                    Bitmap.CompressFormat.JPEG,
                    options,
                    baos
                ) // 这里压缩options%，把压缩后的数据存放到baos中
                if (options > 20) {
                    options -= 20 // 每次都减少10
                }
                Log.d("shopImg", " size 33 = " + baos.toByteArray().size / 1024)
            }

            var isBm: ByteArrayInputStream? = ByteArrayInputStream(baos.toByteArray())
            val newOpts = BitmapFactory.Options()
            // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true
            var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
            newOpts.inJustDecodeBounds = false
            val w = newOpts.outWidth
            val h = newOpts.outHeight
            val hh = 200f
            val ww = 200f
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            var be = 1 // be=1表示不缩放
            if (w > h && w > ww) { // 如果宽度大的话根据宽度固定大小缩放
                be = (newOpts.outWidth / ww).toInt()
            } else if (w < h && h > hh) {
                be = (newOpts.outHeight / hh).toInt()
            }
            if (be <= 0) be = 1
            newOpts.inSampleSize = be // 设置缩放比例
            // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
            // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            isBm = ByteArrayInputStream(baos.toByteArray())
            bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
//        return compressImage(bitmap) // 压缩好比例大小后再进行质量压缩
            if (bitmap.toString().isNullOrEmpty()) {
                bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.img_pxs_defult_small)
            }
            return bitmap;
        }


        fun share(context:Context,title:String, bmp: Bitmap?, url: String) {
            val wxApi = WXAPIFactory.createWXAPI(context, API.WEIXIN_APP_ID, true)
            wxApi?.registerApp(API.WEIXIN_APP_ID)
            val webpage = WXWebpageObject()
            webpage.webpageUrl = url
            Log.d("one share--", "url 111== " + url)
            val msg = WXMediaMessage(webpage)
            msg.title = title
//        msg.description = "网页描述"
            msg.setThumbImage(bmp)
            val req = SendMessageToWX.Req()
            req.transaction = "puxianshengshare"
            req.message = msg
            req.scene = SendMessageToWX.Req.WXSceneSession
//        req.scene = SendMessageToWX.Req.WXSceneTimeline
            wxApi.sendReq(req)
        }
    }
}