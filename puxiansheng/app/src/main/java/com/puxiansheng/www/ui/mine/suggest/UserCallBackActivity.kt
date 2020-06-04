package com.puxiansheng.www.ui.mine.suggest


import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.RecommendOrderAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.synthetic.main.activity_my_suggest_callback.*
import kotlinx.android.synthetic.main.activity_user_suggest.*
import kotlinx.android.synthetic.main.activity_user_suggest.button_back
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.coroutines.launch

class UserCallBackActivity : MyBaseActivity() {

    private lateinit var userSuggestViewModel: UserSuggestViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_my_suggest_callback
    }

    override fun business() {
        userSuggestViewModel = ViewModelProvider(this)[UserSuggestViewModel::class.java]
        initView()
    }


    fun initView(){
        button_back.setOnClickListener {
            onBackPressed()
        }

        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            call_list.addItemDecoration(it)
        }
        lifecycleScope.launch {
            call_list.layoutManager = LinearLayoutManager(this@UserCallBackActivity)
            call_list.adapter = QuestionAdapter(mutableListOf())
            userSuggestViewModel.getRequestCallBack()?.let {
                (call_list.adapter as QuestionAdapter).setMenuData(it)

            }
        }


    }


}