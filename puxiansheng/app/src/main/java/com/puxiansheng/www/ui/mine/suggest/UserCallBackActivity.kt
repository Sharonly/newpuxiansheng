package com.puxiansheng.www.ui.mine.suggest


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_user_suggest.*
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

        lifecycleScope.launch {
            userSuggestViewModel.getRequestCallBack().let {

            }
        }


    }


}