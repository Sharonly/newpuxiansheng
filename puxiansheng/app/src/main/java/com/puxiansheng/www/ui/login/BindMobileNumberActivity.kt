package com.puxiansheng.www.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.www.app.MyActivityManage
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_bind_phone.*
import kotlinx.android.synthetic.main.activity_bind_phone.input_account
import kotlinx.android.synthetic.main.activity_bind_phone.input_vertoken
import kotlinx.android.synthetic.main.activity_bind_phone.requestVerificationCode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class BindMobileNumberActivity : MyBaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_bind_phone
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initView()
    }



private fun initView(){
    button_back.setOnClickListener {
        onBackPressed()
    }

    input_account.addTextChangedListener { editable ->
        editable?.toString()?.let {
            viewModel.userAccount = it
        }
    }
    input_vertoken.addTextChangedListener { editable ->
        editable?.toString()?.let {
            viewModel.verificationCode = it
        }
    }


    requestVerificationCode.setOnClickListener {
        viewModel.requestType = "bind_phone"
        if (viewModel.userAccount == "") {
            Toast.makeText(this, "请先填写手机号码！", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }

        lifecycleScope.launch {
            viewModel.requestVerificationCode()?.let {
                if (it == API.CODE_SUCCESS) {
                    viewModel.startCountDown()
                    requestVerificationCode.isEnabled = false
                    input_vertoken.requestFocus()
                }
            }
        }
    }

        bt_sure.setOnClickListener {
            lifecycleScope.launch {
                viewModel.bindMobileNumber(id = intent.getStringExtra("id")).let { result ->
                    if (result is User) {
                        SpUtils.put(
                            API.LOGIN_USER_ID,
                            result.userID
                        )
                        SpUtils.put(
                            API.LOGIN_USER_TOKEN,
                            result.token
                        )
                        SpUtils.put(
                            API.LOGIN_NICK_NAME,
                            result.name
                        )
                        SpUtils.put(
                            API.LOGIN_ACTUL_NAME,
                            result.actualName
                        )
                        SpUtils.put(
                            API.LOGIN_USER_ICON,
                            result.icon
                        )
                        SpUtils.put(
                            API.LOGIN_USER_PHONE,
                            result.userPhoneNumber
                        )
                        SpUtils.put(
                            API.LOGIN_USER_STATE,
                            1
                        )
                        LiveDataBus.get().with("user")?.value = result
//                        val intent =
//                            Intent(this@BindMobileNumberActivity, MainActivity::class.java)
                        val intent =
                            Intent(this@BindMobileNumberActivity, MainActivity::class.java)
                        startActivity(intent)
                        MyActivityManage.finshActivity("BindMobileNumberActivity")
                    }
                }
            }
        }




        viewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    viewModel.countDown.observe(this, Observer {
            if (it == 0) {
                requestVerificationCode.text = "获取验证码"
                requestVerificationCode.isEnabled = true
            } else {
                requestVerificationCode.text = "${it}秒后可重新获取"
                requestVerificationCode.isEnabled = false
            }
        })

    }


}