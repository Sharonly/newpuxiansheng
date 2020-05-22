package com.puxiansheng.www.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespBindMobilePhone
import com.puxiansheng.util.Regular
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.login.WechatAPI
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_LOGIN_WITH_CODE
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_LOGIN_WITH_PASSWORD
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_REGISTER
import com.puxiansheng.www.ui.main.MainActivity
import com.tencent.mm.opensdk.modelmsg.SendAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login_by_password.*
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class LoginActivity : MyBaseActivity() {
    var context: Context = this@LoginActivity
    var isSeleted = false
    private lateinit var loginViewModel: LoginViewModel
    var loginType = MODE_LOGIN_WITH_PASSWORD


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun business() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initView()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView() {
        tab_login.setOnClickListener {
            tab_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
            tab_login.setTextColor(resources.getColor(R.color.black))
            tab_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
            tab_register.setTextColor(resources.getColor(R.color.gray))
            layout_login_by_password.visibility = View.VISIBLE
            layout_register.visibility = View.GONE
            bt_phone_fast_login.visibility = View.VISIBLE
            bt_login.text = "登录"
            bt_phone_fast_login.text = "手机号快速登录"
            loginType = MODE_LOGIN_WITH_PASSWORD
        }

        tab_register.setOnClickListener {
            tab_login.setTextColor(resources.getColor(R.color.gray))
            tab_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F);
            tab_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            tab_register.setTextColor(resources.getColor(R.color.black))
            layout_login_by_password.visibility = View.GONE
            layout_register.visibility = View.VISIBLE
            ic_selected.visibility = View.VISIBLE
            txt_reader.visibility = View.VISIBLE
            txt_pxs_agreement.visibility = View.VISIBLE
            bt_phone_fast_login.visibility = View.INVISIBLE
            bt_login.text = "注册"
            loginType = MODE_REGISTER
        }

        input_user_account.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.userAccount = it
            }
        }

        input_user_password.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.userPassword = it
            }
        }

        input_user_phonenum.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.userAccount = it
            }
        }

        txt_message_token.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel.verificationCode = it
            }
        }

        forget_password.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        txt_pxs_agreement.setOnClickListener {
            val intent = Intent(this@LoginActivity, AgreementActivity::class.java)
            startActivity(intent)
        }

        ic_selected.setOnClickListener {
            if (!isSeleted) {
                isSeleted = true
                ic_selected.setImageDrawable(resources.getDrawable(R.mipmap.selection))
            } else {
                isSeleted = false
                ic_selected.setImageDrawable(resources.getDrawable(R.mipmap.unchecked))
            }
        }

        bt_phone_fast_login.setOnClickListener {
            if (bt_phone_fast_login.text == "手机号快速登录") {
                layout_login_by_password.visibility = View.GONE
                layout_register.visibility = View.VISIBLE
                ic_selected.visibility = View.INVISIBLE
                txt_reader.visibility = View.INVISIBLE
                txt_pxs_agreement.visibility = View.INVISIBLE
                bt_phone_fast_login.visibility = View.VISIBLE
                bt_phone_fast_login.text = "账号登录"
                loginType = MODE_LOGIN_WITH_CODE
            } else {
                tab_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
                tab_login.setTextColor(resources.getColor(R.color.black))
                tab_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
                tab_register.setTextColor(resources.getColor(R.color.gray))
                layout_login_by_password.visibility = View.VISIBLE
                layout_register.visibility = View.GONE
                bt_phone_fast_login.visibility = View.VISIBLE
                bt_login.text = "登录"
                bt_phone_fast_login.text = "手机号快速登录"
                loginType = MODE_LOGIN_WITH_PASSWORD
            }
        }

        pass_hide.setOnClickListener {
            input_user_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }
        pass_show.setOnClickListener {
            input_user_password.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        bt_login.setOnClickListener {
            loginViewModel.userAccount.let {
                if (!Regular.isPhoneNumber(it)) {
                    input_user_account.error = resources.getString(R.string.login_error_account)
                    return@setOnClickListener
                }
            }

            if (loginType == MODE_LOGIN_WITH_PASSWORD) {
                loginViewModel.userPassword.let {
                    if (!Regular.isPassword(it) && input_user_password.visibility == View.VISIBLE) {
                        input_user_password.error =
                            resources.getString(R.string.login_error_password)
                        return@setOnClickListener
                    }
                }
            }

            if (loginType == MODE_REGISTER || loginType == MODE_LOGIN_WITH_CODE) {
                loginViewModel.verificationCode.let {
                    if (it.length != 6 && txt_message_token.visibility == View.VISIBLE) {
                        txt_message_token.error = resources.getString(R.string.login_error_code)
                        return@setOnClickListener
                    }
                }
            }
            lifecycleScope.launch(Dispatchers.IO) {
                loginViewModel.loginByType(loginType)?.let {
                    if (it is User) {
                        if (loginType == MODE_REGISTER) {
                            loginViewModel.userAccount = input_user_phonenum.text.toString()
                            loginViewModel.loginMode.postValue(MODE_LOGIN_WITH_PASSWORD)
                            LoginSuccessDialog().show(
                                supportFragmentManager,
                                LoginSuccessDialog::class.java.name
                            )
                        } else {
                            SharedPreferencesUtil.put(
                                API.LOGIN_USER_TOKEN,
                                it.token
                            )
                            SharedPreferencesUtil.put(
                                API.LOGIN_NICK_NAME,
                                it.nickname
                            )
                            SharedPreferencesUtil.put(
                                API.LOGIN_ACTUL_NAME,
                                it.actualName
                            )
                            SharedPreferencesUtil.put(
                                API.LOGIN_USER_ICON,
                                it.icon
                            )
                            SharedPreferencesUtil.put(
                                API.LOGIN_USER_PHONE,
                                it.userPhoneNumber
                            )
                            SharedPreferencesUtil.put(API.LOGIN_USER_STATE, 1)
                            API.setAuthToken(it.token)
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            LiveDataBus.get().with("user")?.value = it
                            startActivity(intent)
                        }
                    }
                }
            }
        }

        wechat_login.setOnClickListener {
            WechatAPI.instance?.sendReq(
                SendAuth.Req().apply {
                    scope = "snsapi_userinfo"
                    state = "wechat_sdk_demo_test"
                }
            )
        }

        requestVerificationCode.setOnClickListener {
            if (loginViewModel.userAccount == "") {
                Toast.makeText(context, "请先填写手机号码！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                loginViewModel.requestVerificationCode()?.let {
                    if (it == API.CODE_SUCCESS) {
                        loginViewModel.startCountDown()
                        requestVerificationCode.isEnabled = false
                    }
                }
            }
        }

        loginViewModel.toastMsg.observe(this@LoginActivity, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        loginViewModel.countDown.observe(this@LoginActivity, Observer {
            if (it == 0) {
                requestVerificationCode.text = "获取验证码"
                requestVerificationCode.isEnabled = true
            } else {
                requestVerificationCode.text = "${it}秒后可重新获取"
                requestVerificationCode.isEnabled = false
            }
        })


        loginViewModel.wechatCode.observe(this@LoginActivity, Observer { weChatCode ->
            weChatCode?.let { code ->
                loginViewModel.wechatLoginCode = code
                loginViewModel.wechatCode.postValue(null)

                lifecycleScope.launch(Dispatchers.IO) {
                    loginViewModel.loginByType(LoginViewModel.MODE_LOGIN_WITH_WECHAT)
                        ?.let { result ->
                            loginViewModel.wechatLoginCode = ""
                            if (result is HttpRespBindMobilePhone && result.code == API.CODE_BAND_MOBILE_NUMBER) {

                            }
                            if (result is User) {
                                SharedPreferencesUtil.put(
                                    API.LOGIN_USER_TOKEN,
                                    result.token
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_NICK_NAME,
                                    result.nickname
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_ACTUL_NAME,
                                    result.actualName
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_USER_ICON,
                                    result.icon
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_USER_PHONE,
                                    result.userPhoneNumber
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_USER_STATE,
                                    1
                                )
                                API.setAuthToken(result.token)
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                }
            }
        })
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        loginViewModel?.wechatCode?.postValue(intent?.extras?.getString("authCode"))
    }

}