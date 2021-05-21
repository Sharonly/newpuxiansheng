package com.puxiansheng.www.ui.release

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentReleaseBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.umeng.analytics.MobclickAgent

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

class ReleaseFragment : AppFragment() {
    private lateinit var releaseViewModel: ReleaseViewModel
    private lateinit var appModel: MainViewModel
    var name = ""
    var phone = ""
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext= context
        releaseViewModel = ViewModelProvider(this)[ReleaseViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentReleaseBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            releaseViewModel.requestBannerImage("api_issue_shop_images")?.let { banners ->
//                topBannerView.setBannerImages(banners)
            }
        }

        transferOut.setOnClickListener {
            name = SpUtils.get(API.LOGIN_ACTUL_NAME, "").toString()
            phone = SpUtils.get(API.LOGIN_USER_PHONE, "").toString()

            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
//                if (name.isNotEmpty() && phone.isNotEmpty()) {
                val intent =
                    Intent(requireActivity(), InsertOrUpdateTransferOutOrderActivity::class.java)
                intent.putExtra("shopID", "0")
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME,"InsertOrUpdateTransferOutOrderActivity")
                MobclickAgent.onEvent(mContext, UMengKeys.LOGIN_USER_ID, SpUtils.get(
                    API.LOGIN_USER_ID,
                    0
                ).toString())
//                } else {
//                    Toast.makeText(requireActivity(), "需要先保存个人信息才能发布哟", Toast.LENGTH_SHORT)
//
//                }
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
        transferIn.setOnClickListener {
            name = SpUtils.get(API.LOGIN_ACTUL_NAME, "").toString()
            phone = SpUtils.get(API.LOGIN_USER_PHONE, "").toString()
            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
//                if (name.isNotEmpty() && phone.isNotEmpty()) {
                val intent =
                    Intent(requireActivity(), InsertOrUpdateTransferInOrderActivity::class.java)
                intent.putExtra("shopID", "0")
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME,"InsertOrUpdateTransferInOrderActivity")
                MobclickAgent.onEvent(mContext, UMengKeys.LOGIN_USER_ID, SpUtils.get(
                    API.LOGIN_USER_ID,
                    0
                ).toString())
//                } else {
//                    Toast.makeText(requireActivity(), "需要先保存个人信息才能发布哟", Toast.LENGTH_SHORT)
//                }
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }.root

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("ReleaseFragment")
//    }
//
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("ReleaseFragment")
//    }
}