package com.puxiansheng.www.ui.order.dialog

import android.content.Intent
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
import com.puxiansheng.www.databinding.DialogMoreManagerBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailViewModel
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXTextObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
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
//    private lateinit var inViewModel: TransferInOrderDetailViewModel
//    private lateinit var businessViewModel: InvestBusnessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        outViewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
        isCancelable = true
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.window?.let { window ->
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                window.setGravity(Gravity.BOTTOM)
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = DialogMoreManagerBinding.inflate(inflater).apply {
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


        binding.btShare.setOnClickListener {
                 shareText()

        }


        btCancel.setOnClickListener { dismiss() }

    }.root


    /**
     * 举例分享文本类型, 其它类型可以去  https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Share_and_Favorites/Android.html
     *
     * 注意！！！！ 微信平台的debug签名和release签名
     */
    private fun shareText(){
        val wxApi = WXAPIFactory.createWXAPI(requireContext(), API.API_APP_ID, false);
        wxApi?.registerApp(API.API_APP_ID);

        val textObj = WXTextObject()
        textObj.text = "Google"

        //用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        val msg = WXMediaMessage()
        msg.mediaObject = textObj
        msg.description = "这是Android端的测试分享信息"

        val req = SendMessageToWX.Req()
        req.transaction ="2020-06-09"

        req.message = msg
       // req.scene = mTargetScene
        wxApi?.sendReq(req)

    }

}