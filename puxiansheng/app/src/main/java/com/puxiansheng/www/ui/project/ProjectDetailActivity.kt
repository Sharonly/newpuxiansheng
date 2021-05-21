package com.puxiansheng.www.ui.project

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.url
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.login.LoginSuccessDialog
import com.puxiansheng.www.ui.project.adapter.BannerAdapter
import kotlinx.android.synthetic.main.activity_project_detail.*
import kotlinx.android.synthetic.main.activity_project_detail.address
import kotlinx.android.synthetic.main.activity_project_detail.bt_favor
import kotlinx.android.synthetic.main.activity_project_detail.shop_title
import kotlinx.coroutines.launch
import java.util.*

class ProjectDetailActivity : MyBaseActivity() {
    private lateinit var viewModel: ProjectDetailViewModel
    var shopId = ""
    var projectName = ""
    var index = 0
    var type = 3
    var isFavor = false
    private var bannerList = ArrayList<String>()


    override fun getLayoutId(): Int {
        return R.layout.activity_project_detail
    }

    override fun business() {
        MyScreenUtil.setStateBarStyle(this, true, R.color.color81, false)
        viewModel = ViewModelProvider(this)[ProjectDetailViewModel::class.java]
        shopId =  intent.getLongExtra("shopId", 0L).toString()
        initView()
    }

    private fun initView() {
        button_back.setOnClickListener{
            onBackPressed()
        }

        bt_favor.setOnClickListener {
            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.favorite(
                        objectID = shopId,
                        type = type
                    )?.let { result ->
                        if (result.data?.result == 0) {
                            isFavor = false
                            bt_favor.setImageResource(R.mipmap.ic_favor_white)
//                            btFavor.text = "收藏"
                        } else {
                            isFavor = true
                            bt_favor.setImageResource(R.mipmap.ic_favor_red)
//                            btFavor.text = "取消收藏"
                        }
                        Toast.makeText(
                            this@ProjectDetailActivity,
                            result.msg,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        lifecycleScope.launch {
            viewModel.requestProjectDetail(shopId)
                ?.let { project ->
                    if(project.video.length>5) {
                        bannerList.add(project.video)
                    }
                    if(project.img_ids.size >0) {
                        bannerList.addAll(project.img_ids)
                    }

                    Log.e("ProjectDetail"," bannerList = "+bannerList)
                    var bannerAdapter = BannerAdapter(this@ProjectDetailActivity)
                    if(bannerList.size>0) {
                        bannerAdapter.update(bannerList)
                        banner.adapter = bannerAdapter
                    }

                    if (project.is_collect == 1) {
                        isFavor = true
                        bt_favor.setImageResource(R.mipmap.ic_favor_red)
                    } else {
                        isFavor = false
                        bt_favor.setImageResource(R.mipmap.ic_favor_white)
                    }
                    icon.url(project.theme_img)
                    shop_title.text = project.title
                    projectName= project.title
                    industry_lable.text = project.category_str
                    sub_title.text = project.brand
                    company.text = project.brand
                    address.text = project.shop_address
                    invest_money.text = project.invest_money

                    lable_add.text = project.is_join
                    lable_study.text = project.is_trainee

                    txt_one.text = project.project_situation
                    txt_two.text = project.superiority
                    txt_three.text = project.operation_status
                    txt_four.text = project.environment
                    txt_five.text = project.project_situation

                }
        }

        connect_phone.setOnClickListener {
            ProjectApproveDialog(shopId,projectName).show(
                supportFragmentManager,
                ProjectApproveDialog::class.java.name
            )
        }

    }
}