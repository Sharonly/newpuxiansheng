package com.puxiansheng.www.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.www.databinding.DialogConnectKefuBinding
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.home.viewmodel.ConnectViewModel
import com.puxiansheng.www.ui.mine.MineViewModel
import com.puxiansheng.www.ui.mine.ServiceActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailViewModel
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.launch

class FastConnectDialog : DialogFragment() {
    private lateinit var binding: DialogConnectKefuBinding
    private lateinit var viewModel: ConnectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ConnectViewModel::class.java]
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
    ): View? = DialogConnectKefuBinding.inflate(inflater).apply {
        binding = this
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            viewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                btOnline.setOnClickListener {
                    val intent = Intent(context, ServiceActivity::class.java)
                    intent.putExtra("title", "我的客服")
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "ServiceActivity")
                }
            }
        }

        btPhone.setOnClickListener {
            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${4008068999}")
                startActivity(this)
            }
        }

        btCancel.setOnClickListener {
            dismiss()
        }

    }.root
}