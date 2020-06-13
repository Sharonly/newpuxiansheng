package com.puxiansheng.www.ui.order.dialog

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import com.puxiansheng.www.databinding.DialogMoreManagerBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailViewModel
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import gdut.bsx.share2.Share2
import gdut.bsx.share2.ShareContentType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MoreManagerDialog(
    private val shopID: String,
    private val type: Int,
    private val isfavorite: Int
) : DialogFragment() {
    private lateinit var binding: DialogMoreManagerBinding
    private lateinit var outViewModel: TransferOutOrderDetailViewModel
    var shareUrl:String = ""

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

        if (isfavorite == 1) {
            btFavor.text = "取消收藏"
        } else {
            btFavor.text = "收藏"
        }

        btFavor.setOnClickListener {
            if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                lifecycleScope.launch {
                    outViewModel.favorite(
                        objectID = shopID,
                        type = type
                    )?.let { result ->
                        if (result.data?.result == 0) {
                            btFavor.text = "收藏"
                        } else {
                            btFavor.text = "取消收藏"
                            dismiss()
                        }
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }


        lifecycleScope.launch {
            when (type) {
                0 -> outViewModel.getConfigInfo("transfer_share_url")?.let { configInfo ->
                    shareUrl = "$configInfo$shopID.html"
                }

                1 -> outViewModel.getConfigInfo("find_shop_share_url")?.let { configInfo ->
                    shareUrl = "$configInfo$shopID.html"
                }

                2 -> outViewModel.getConfigInfo("article_share_url")?.let { configInfo ->
                    shareUrl = "$configInfo$shopID.html"
                }
            }

        }
        binding.btShare.setOnClickListener {
//            shareText(shareUrl)
            share(shareUrl)
        }

        btCancel.setOnClickListener { dismiss() }

    }.root


    /**
     * 举例分享文本类型, 其它类型可以去  https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Share_and_Favorites/Android.html
     *
     * 注意！！！！ 微信平台的debug签名和release签名
     */
    private fun shareText(url: String) {
        val wxApi = WXAPIFactory.createWXAPI(requireContext(), API.WEIXIN_APP_ID, true)
        wxApi?.registerApp( API.WEIXIN_APP_ID)
        val webpage = WXWebpageObject()
        webpage.webpageUrl = url

        val msg = WXMediaMessage(webpage)
        msg.title = "分享 "
        msg.description = "网页描述"
        val thumbBmp =
            BitmapFactory.decodeResource(resources, R.mipmap.app_logo)
//        msg.thumbData = Util.bmpToByteArray(thumbBmp, true)
        val req = SendMessageToWX.Req()
//        req.transaction = buildTransaction("webpage")
        req.message = msg
        req.userOpenId = API.WEIXIN_APP_ID
        wxApi?.sendReq(req)
    }

    private fun share(url: String){
        Share2.Builder(requireActivity())
            .setContentType(ShareContentType.TEXT)
            .setTextContent(url)
            .setTitle("分享")
            .build()
            .shareBySystem()
    }

}