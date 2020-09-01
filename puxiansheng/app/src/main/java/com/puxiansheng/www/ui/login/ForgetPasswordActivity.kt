package com.puxiansheng.www.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.Regular
import com.puxiansheng.www.R
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_FORGET_PASSWORD
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.activity_forget_password.button_back
import kotlinx.android.synthetic.main.activity_forget_password.icon_eye
import kotlinx.android.synthetic.main.activity_forget_password.input_password_again
import kotlinx.android.synthetic.main.activity_forget_password.layout_password_again
import kotlinx.android.synthetic.main.activity_forget_password.requestVerificationCode
import kotlinx.android.synthetic.main.layout_login_by_password.*
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgetPasswordActivity : AppCompatActivity(R.layout.activity_forget_password) {
    var context: Context = this@ForgetPasswordActivity
    private lateinit var loginViewModel: LoginViewModel
    var passIsShow = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initView()
    }


    private fun initView(){
        button_back.setOnClickListener {
            onBackPressed()
        }

        input_account.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.userAccount = it
            }
        }
        input_vertoken.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.verificationCode = it
            }
        }

        txt_new_password.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.newPassword = it
            }
        }
        input_password_again.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.newPasswordAgain = it
            }
        }

        requestVerificationCode.setOnClickListener {
            loginViewModel.requestType = "reset_pwd"
            if (loginViewModel.userAccount == "") {
                Toast.makeText(context, "请先填写手机号码！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                loginViewModel.requestVerificationCode()?.let {
                    if (it == API.CODE_SUCCESS) {
                        loginViewModel.startCountDown()
                        requestVerificationCode.isEnabled = false
                        input_vertoken.requestFocus()
                    }
                }
            }
        }

        bt_go_to.setOnClickListener {
            if(bt_go_to.text.contains("下一步")){
                loginViewModel.userAccount.let {
                    if (!Regular.isPhoneNumber(it)) {
                        input_account.error = resources.getString(R.string.login_error_account)
                        return@setOnClickListener
                    }
                }

                loginViewModel.verificationCode.let {
                    if (it.length != 6 && txt_message_token.visibility == View.VISIBLE) {
                        input_vertoken.error = resources.getString(R.string.login_error_code)
                        return@setOnClickListener
                    }
                }
                input_account.visibility = View.GONE
                layout_vertoken.visibility = View.GONE
                txt_new_password.visibility = View.VISIBLE
                layout_password_again.visibility = View.VISIBLE
                icon_eye.visibility = View.VISIBLE
                            bt_go_to.text = "确认"
            }else if(bt_go_to.text=="确认"){
                loginViewModel.newPassword.let {
                    if (!Regular.isPassword(it)) {
                        txt_new_password.error = resources.getString(R.string.login_error_password)
                        return@setOnClickListener
                    }
                }

                loginViewModel.newPasswordAgain.let {
                    if (!Regular.isPassword(it)) {
                        input_password_again.error = resources.getString(R.string.login_error_password)
                        return@setOnClickListener
                    }
                }

                if(loginViewModel.newPasswordAgain!=loginViewModel.newPassword){
                    Toast.makeText(context, "两次密码不一致", Toast.LENGTH_SHORT).show()
                }

                lifecycleScope.launch(Dispatchers.IO) {
                    loginViewModel.loginByType(MODE_FORGET_PASSWORD)?.let {
                        if (it is User) {
                            PasswordChangeDialog().show(
                                supportFragmentManager,
                                PasswordChangeDialog::class.java.name)
                        }
                    }
                }
            }
        }

        icon_eye.setOnClickListener {
            if(!passIsShow){
                input_password_again.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                icon_eye.setImageResource(R.mipmap.ic_yincang)
                passIsShow = true
            }else {
                input_password_again.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                icon_eye.setImageResource(R.mipmap.ic_xianshi)
                passIsShow = false
            }

        }


        loginViewModel.toastMsg.observe(this@ForgetPasswordActivity, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        loginViewModel.countDown.observe(this@ForgetPasswordActivity, Observer {
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