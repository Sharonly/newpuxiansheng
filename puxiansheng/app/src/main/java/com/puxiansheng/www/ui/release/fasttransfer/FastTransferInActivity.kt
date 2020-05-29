package com.puxiansheng.www.ui.release.fasttransfer

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.ext.toast
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.urlBg
import kotlinx.android.synthetic.main.activity_fast_transfer.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FastTransferInActivity : MyBaseActivity() {
    private lateinit var viewModel: SimpleOrderViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_fast_transfer
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[SimpleOrderViewModel::class.java]
        initView()
    }

    fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }
        bar_title.text = "快速找店"
        input_phone.addTextChangedListener {
            viewModel.phone = it.toString()
        }

//        bg_img.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_fast_find_images")?.let { it ->
                img_1.urlBg(it[0].imageUrl)
                img_2.urlBg(it[1].imageUrl)
                img_3.urlBg(it[2].imageUrl)
            }
        }

        submit.setOnClickListener {
            viewModel.city = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
            lifecycleScope.launch {
                viewModel.submitSimpleTransferInOrder().let {
                    if (it.succeeded) {
                        it as APIRst.Success
                        it.data.msg.toast(this@FastTransferInActivity)
                        if (it.data.code == API.CODE_SUCCESS) {
                            delay(2000)
                            onBackPressed()
                        }
                    }
                }
            }
        }


    }

}