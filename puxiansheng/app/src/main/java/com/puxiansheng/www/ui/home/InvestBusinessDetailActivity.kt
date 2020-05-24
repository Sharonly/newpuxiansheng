package com.puxiansheng.www.ui.home


import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.url
import com.puxiansheng.www.ui.order.TransferInOrderDetailViewModel
import kotlinx.android.synthetic.main.fragment_invest_business_detail.*
import kotlinx.coroutines.launch


class InvestBusinessDetailActivity : MyBaseActivity() {
    private lateinit var viewModel: BusinessDetailViewModel
    private var imges = mutableListOf<String>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_invest_business_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[BusinessDetailViewModel::class.java]
        initView()
    }

    @SuppressLint("SetTextI18n")
    fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launch {
            viewModel.requestInvestBusinessDetail(intent.getIntExtra("id", 0).toString())
                ?.let { business ->
                    var phone = business.contact_phone
//                    imges.add(business.thumb_img)
                    image_switcher.url(business.thumb_img)
                    var insdustry = business.trades
                    var money = business.investment
                    shop_title.text = business.name
                    shop_industry.text = "所属行业：$insdustry"
                    invest_money.text = "投资资金：$money"
                    connect_phone.text = "加盟热线：$phone"


                }



        }
    }

}

