package com.puxiansheng.www.ui.order.dialog

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.www.R
import com.puxiansheng.www.common.BitMapUtil
import com.puxiansheng.www.databinding.DialogMoreManagerBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailViewModel
import com.tencent.map.tools.Util
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import gdut.bsx.share2.Share2
import gdut.bsx.share2.ShareContentType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MoreManagerDialog(
    private val shopId: String,
    private val shopTitle: String,
    private val shopImg: String,
    private val shopUrl: String? = null,
    private val type: Int,
    private val isfavorite: Int
) : DialogFragment() {
    private lateinit var binding: DialogMoreManagerBinding
    private lateinit var outViewModel: TransferOutOrderDetailViewModel
    var shareUrl: String = ""
    var shopPath: String = ""
    private var bitMapUtil = BitMapUtil()
    var shopBmp: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outViewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DialogMoreManagerBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner
        Log.e("shopImg", " shopImg = " + shopImg)
        if (type == 999) {
            btFavor.visibility = View.GONE
        } else {
            btFavor.visibility = View.VISIBLE
            if (isfavorite == 1) {
                btFavor.text = "取消收藏"
            } else {
                btFavor.text = "收藏"
            }
        }

        if (shopImg.isNullOrEmpty()) {
            shopBmp = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
        } else {
            shopBmp = bitMapUtil.returnBitMap(shopImg)
            Log.d("shopImg", " shopBmp = " + shopBmp + "  --- " + bitMapUtil.returnBitMap(shopImg))
            val baos = ByteArrayOutputStream()
            shopBmp?.compress(Bitmap.CompressFormat.JPEG, 70, baos)
            Log.d("shopImg", " shopBmp.size = " + baos.toByteArray().size)
        }

        btFavor.setOnClickListener {
            if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                lifecycleScope.launch {
                    outViewModel.favorite(
                        objectID = shopId,
                        type = type
                    )?.let { result ->
                        if (result.data?.result == 0) {
                            btFavor.text = "收藏"
                        } else {
                            btFavor.text = "取消收藏"
                        }
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                            .show()
                        dismiss()
                    }
                }
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }


        lifecycleScope.launch {
            when (type) {
                0 -> {
                    outViewModel.getConfigInfo("transfer_share_url")?.let { configInfo ->
                        shareUrl = "$configInfo$shopId.html"
                    }
                    shopPath = "packageA/pages/shopDetail/index?id=$shopId"
                }
                1 -> {
                    outViewModel.getConfigInfo("find_shop_share_url")?.let { configInfo ->
                        shareUrl = "$configInfo$shopId.html"
                    }
                    shopPath = "packageA/pages/findDetail/index?id=$shopId"
                }
                2 -> {
                    shareUrl = shopUrl.toString()
                    Log.d("one share--","shareUrl == "+shareUrl)
//                    outViewModel.getConfigInfo("article_share_url")?.let { configInfo ->
//                        shareUrl = "$configInfo$shopId.html"
////                        shareUrl = shopUrl.toString()
//                    }
                    shopPath = "packageA/pages/webView/index?url=$shopUrl"

                }
                999 -> {
                    outViewModel.getConfigInfo("transfer_share_url")?.let { configInfo ->
                        shareUrl = "$configInfo$shopId.html"
                    }
                    shopPath = "packageA/pages/shopDetail/index?id=$shopId"
                }
            }

        }
        binding.btShare.setOnClickListener {
            if (shopImg.isNullOrEmpty()) {
                shopBmp = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
            } else {
                shopBmp = bitMapUtil.returnBitMap(shopImg)
                if (shopBmp != null) {
                    shopBmp.let {
                        shopBmp = compressScale(it!!)
                    }
                } else {
                    shopBmp = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
                }
            }
//            ShareObject(shareUrl)
            shareUrl(shareUrl)
//            share(shareUrl)
            dismiss()
        }
        btCancel.setOnClickListener { dismiss() }

    }.root


    /**
     * 举例分享文本类型, 其它类型可以去  https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Share_and_Favorites/Android.html
     *
     * 注意！！！！ 微信平台的debug签名和release签名
     */
    private fun shareUrl(url: String) {
        val wxApi = WXAPIFactory.createWXAPI(requireContext(), API.WEIXIN_APP_ID, true)
        wxApi?.registerApp(API.WEIXIN_APP_ID)
        val webpage = WXWebpageObject()
        webpage.webpageUrl = url
        Log.d("one share--","url 111== "+url)
        val msg = WXMediaMessage(webpage)
        msg.title = shopTitle
//        msg.description = "网页描述"
        msg.setThumbImage(shopBmp)
        val req = SendMessageToWX.Req()
        req.transaction = "puxianshengshare"
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
//        req.scene = SendMessageToWX.Req.WXSceneTimeline
        wxApi.sendReq(req)
    }

    private fun ShareObject(url: String) {
        val wxApi = WXAPIFactory.createWXAPI(requireContext(), API.WEIXIN_APP_ID, true)
        wxApi?.registerApp(API.WEIXIN_APP_ID)
        val miniProgramObj = WXMiniProgramObject()
        miniProgramObj.webpageUrl = url // 兼容低版本的网页链接
        miniProgramObj.miniprogramType =
            WXMiniProgramObject.MINIPTOGRAM_TYPE_RELEASE // 正式版:0，测试版:1，体验版:2
        miniProgramObj.userName = "gh_3ce1434594cf" // 小程序原始id
        miniProgramObj.path = shopPath //小程序页面路径, req.path = "pages/xxx/xxx?key=value&key=value";
        val msg = WXMediaMessage(miniProgramObj)
        msg.title = shopTitle// 小程序消息title
//        msg.description = "小程序消息Desc" // 小程序消息desc
        msg.setThumbImage(shopBmp)
        val req = SendMessageToWX.Req()
        req.transaction = "puxianshengshare"
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession // 目前只支持会话
        wxApi.sendReq(req)
    }

    private fun share(url: String) {
        Share2.Builder(requireActivity())
            .setContentType(ShareContentType.IMAGE)
            .setTextContent(url)
            .setTitle(shopTitle)
            .build()
            .shareBySystem()
    }


    /**
     * 图片按比例大小压缩方法
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    fun compressScale(image: Bitmap): Bitmap? {
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
            bitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
        }
        return bitmap;
    }

    /**
     * 质量压缩方法
     * @param image
     * @return
     */
    fun compressImage(image: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 30, baos) // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 90
        while (baos.toByteArray().size / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset() // 重置baos即清空baos
            image.compress(
                Bitmap.CompressFormat.JPEG,
                options,
                baos
            ) // 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10 // 每次都减少10
        }
        val isBm =
            ByteArrayInputStream(baos.toByteArray()) // 把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null)
    }

}