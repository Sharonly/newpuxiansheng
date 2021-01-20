package com.puxiansheng.www.ui.login

import android.content.Context
import android.content.Intent
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespBindMobilePhone
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.util.Regular
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.login.WechatAPI
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_LOGIN_WITH_CODE
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_LOGIN_WITH_PASSWORD
import com.puxiansheng.www.ui.login.LoginViewModel.Companion.MODE_REGISTER
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.ui.mine.ServiceActivity
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_login_by_password.*
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class LoginActivity : MyBaseActivity() {
   // var context: Context ?= null
    var isSeleted = false
    private  var loginViewModel: LoginViewModel? = null
    var loginType = MODE_LOGIN_WITH_PASSWORD
    var loginTypeName = ""
    var passIsShow = false
    var isLogin = false

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
        return R.layout.activity_login
    }

    override fun business() {
       // context = this
        MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "LoginActivity")
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        initView()
    }


    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        tab_login.setOnClickListener {
            initLoginView()
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
            loginTypeName = "注册"
            loginViewModel?.requestType = "register"
        }

        input_user_account.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel?.userAccount = it
            }
        }

        input_user_password.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel?.userPassword = it
            }
        }

        input_user_phonenum.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel?.userAccount = it
            }
        }

        txt_message_token.addTextChangedListener { editable ->
            editable?.toString()?.let {
                loginViewModel?.verificationCode = it
            }
        }

        forget_password.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            loginViewModel?.getConfigInfo("protocol_url")?.let { configInfo ->
                txt_pxs_agreement.setOnClickListener {
                    val intent = Intent(this@LoginActivity, ServiceActivity::class.java)
                    intent.putExtra("title", "用户协议")
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                }
            }
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
                input_user_phonenum.setText("")
                txt_message_token.setText("")
                ic_selected.visibility = View.INVISIBLE
                txt_reader.visibility = View.INVISIBLE
                txt_pxs_agreement.visibility = View.INVISIBLE
                bt_phone_fast_login.visibility = View.VISIBLE
                bt_phone_fast_login.text = "账号登录"
                loginType = MODE_LOGIN_WITH_CODE
                loginTypeName = "账号登录"
                loginViewModel?.requestType = "login"
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
                loginTypeName = "手机号快速登录"
            }
        }


        ic_eye.setOnClickListener {
            if (!passIsShow) {
                input_user_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ic_eye.setImageResource(R.mipmap.ic_yincang)
                passIsShow = true
            } else {
                input_user_password.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ic_eye.setImageResource(R.mipmap.ic_xianshi)
                passIsShow = false
            }

        }

        bt_login.setOnClickListener {
            loginViewModel?.userAccount.let {
                if (!Regular.isPhoneNumber(it)) {
                    if(loginType == MODE_LOGIN_WITH_PASSWORD) {
                        input_user_account.error = resources.getString(R.string.login_error_account)
                    }else if(loginType == MODE_REGISTER || loginType == MODE_LOGIN_WITH_CODE){
                        input_user_phonenum.error = resources.getString(R.string.login_error_account)
                    }
                    return@setOnClickListener
                }
            }

            if (loginType == MODE_LOGIN_WITH_PASSWORD) {
                loginViewModel?.userAccount = input_user_account.text.toString()
                loginViewModel?.userPassword = input_user_password.text.toString()
                loginViewModel?.userPassword.let {
                    if (!Regular.isPassword(it) && input_user_password.visibility == View.VISIBLE) {
                        input_user_password.error =
                            resources.getString(R.string.login_error_password)
                        return@setOnClickListener
                    }
                }
            }

            if (loginType == MODE_REGISTER || loginType == MODE_LOGIN_WITH_CODE) {
                loginViewModel?.userAccount = input_user_phonenum.text.toString()
                loginViewModel?.verificationCode = txt_message_token.text.toString()
                loginViewModel?.verificationCode.let {
                    if (it?.length != 6 && txt_message_token.visibility == View.VISIBLE) {
                        txt_message_token.error = resources.getString(R.string.login_error_code)
                        return@setOnClickListener
                    }
                }
            }

            if (loginType == MODE_REGISTER) {
                if (!isSeleted) {
                    Toast.makeText(this, "请先勾选用户协议", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            lifecycleScope.launch() {
                if (!isLogin) {
                    isLogin = true
                    MobclickAgent.onEvent(this@LoginActivity, UMengKeys.LOGIN_TYPE, loginTypeName)

                    loginViewModel?.loginByType(loginType)?.let {
                        println("登录返回-->${it}")
                        if (it is User) {
                            if (loginType == MODE_REGISTER) {
                                loginViewModel?.loginMode?.postValue(MODE_LOGIN_WITH_PASSWORD)
                                LoginSuccessDialog(it.tipsMsg).show(supportFragmentManager, LoginSuccessDialog::class.java.name)
                                input_user_phonenum.setText("")
                                txt_message_token.setText("")
                                initLoginView()
                            } else {
                                MobclickAgent.onProfileSignIn(it.userID.toString())
                                SharedPreferencesUtil.put(
                                    API.LOGIN_USER_TOKEN,
                                    it.token
                                )
                                SharedPreferencesUtil.put(
                                    API.LOGIN_NICK_NAME,
                                    it.name
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
                                LiveDataBus.get().with("user")?.value = it
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                        isLogin = false
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
            loginTypeName = "微信登录"
        }

        requestVerificationCode.setOnClickListener {
            loginViewModel?.userAccount = input_user_phonenum.text.toString()
            if (loginViewModel?.userAccount == "") {
                Toast.makeText(this, "请先填写手机号码！", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            loginViewModel?.userAccount.let {
                if (!Regular.isPhoneNumber(it)) {
                    input_user_phonenum.error = resources.getString(R.string.login_error_account)
//                    input_user_account.error = resources.getString(R.string.login_error_account)
                    return@setOnClickListener
                }
            }
            lifecycleScope.launch {
                loginViewModel?.requestVerificationCode()?.let {
                    if (it == API.CODE_SUCCESS) {
                        loginViewModel?.startCountDown()
                        requestVerificationCode.isEnabled = false
                    }
                }
            }
        }

        loginViewModel?.toastMsg?.observe(this@LoginActivity, Observer {
            Log.d("login"," toastMsg = "+it)
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            isLogin = false
        })
        loginViewModel?.countDown?.observe(this@LoginActivity, Observer {
            if (it == 0) {
                requestVerificationCode.text = "获取验证码"
                requestVerificationCode.isEnabled = true
            } else {
                requestVerificationCode.text = "${it}秒后可重新获取"
                requestVerificationCode.isEnabled = false
            }
        })


        loginViewModel?.wechatCode?.observe(this@LoginActivity, Observer { weChatCode ->
            weChatCode?.let { code ->
                Log.d("intent", " MODE_LOGIN_WITH_WECHAT = " + code)
                if (code.isNotEmpty()) {
                    loginViewModel?.wechatLoginCode = code
                    if (loginViewModel?.wechatLoginCode?.isNotEmpty()!!) {
                        lifecycleScope.launch() {
                            Log.d("intent", " loginByType(LoginViewModel.MODE_LOGIN_WITH_WECHAT) ")
                            if (!isLogin) {
                                isLogin = true
                                loginViewModel?.loginByType(LoginViewModel.MODE_LOGIN_WITH_WECHAT)
                                    ?.let { result ->
                                        loginViewModel?.wechatLoginCode = ""
                                        if (result is HttpRespBindMobilePhone && result.code == API.CODE_BAND_MOBILE_NUMBER) {
                                            val intent =
                                                Intent(
                                                    this@LoginActivity,
                                                    BindMobileNumberActivity::class.java
                                                )
                                            intent.putExtra(
                                                "id",
                                                result.dataObject?.result.toString() ?: "-99"
                                            )
                                            this@LoginActivity.startActivity(intent)
                                        } else {
                                            if (result is User) {
                                                Log.d("---login--", "is User")
                                                MobclickAgent.onProfileSignIn("WeChat",result.userID.toString())
                                                SharedPreferencesUtil.put(
                                                    API.LOGIN_USER_ID,
                                                    result.userID
                                                )
                                                SharedPreferencesUtil.put(
                                                    API.LOGIN_USER_TOKEN,
                                                    result.token
                                                )
                                                SharedPreferencesUtil.put(
                                                    API.LOGIN_NICK_NAME,
                                                    result.name
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
                                                SharedPreferencesUtil.put(API.LOGIN_USER_STATE, 1)
                                                API.setAuthToken(result.token)
                                                LiveDataBus.get().with("user")?.value = result
                                                val intent =
                                                    Intent(
                                                        this@LoginActivity,
                                                        MainActivity::class.java
                                                    )
                                                startActivity(intent)
                                                finish()
                                            }
                                        }
                                        isLogin = false
                                    }
                            }
                        }
                    }
                }
            }
        })
    }

    private fun initLoginView() {
        tab_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F);
        tab_login.setTextColor(resources.getColor(R.color.black))
        tab_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
        tab_register.setTextColor(resources.getColor(R.color.gray))
        input_user_account.setText("")
        input_user_phonenum.setText("")
        input_user_password.setText("")
        layout_login_by_password.visibility = View.VISIBLE
        layout_register.visibility = View.GONE
        bt_phone_fast_login.visibility = View.VISIBLE
        bt_login.text = "登录"
        bt_phone_fast_login.text = "手机号快速登录"
        loginType = MODE_LOGIN_WITH_PASSWORD
        loginTypeName = "手机号快速登录"
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.d("---intent", " intent?.extras?  " + intent?.extras?.getString("authCode"))
        loginViewModel?.wechatCode?.postValue(intent?.extras?.getString("authCode"))
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("LoginActivity")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("LoginActivity")
//    }

}