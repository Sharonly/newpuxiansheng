package com.puxiansheng.www.ui.mine.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentSettingBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.launch

class SettingActivity : MyBaseActivity() {
    private lateinit var appModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
    }

    override fun business() {
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        settingViewModel = ViewModelProvider(this)[SettingViewModel::class.java]
        initView()
    }


    fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launch {
            settingViewModel.getConfigInfo("about_us_url")?.let { configInfo ->
                user_company.setOnClickListener {
                    val intent = Intent(this@SettingActivity, AboutUsActivity::class.java)
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                }
            }
        }

        user_phone_num.text = SharedPreferencesUtil.get(API.LOGIN_USER_PHONE, "").toString()
        user_phone_num.setOnClickListener {
            val intent = Intent(this, UserSettingActivity::class.java)
            startActivity(intent)
        }
        change_password.setOnClickListener {
//            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
//                .navigate(R.id.action_settingFragment_to_resetPasswordFragment)

            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

//        userSuggest.setOnClickListener {
//            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
//                .navigate(R.id.action_settingFragment_to_userSuggestFragment)
//        }


        var myAppInfo =
            this?.applicationContext?.packageManager?.getPackageInfo(this?.packageName, 0)
        curren_version.text = myAppInfo?.versionName


        logout.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.logout()?.let {
                    if (it.code == API.CODE_SUCCESS) {
                        appModel.currentUser.value?.copy(loginState = 0)?.let { user ->
                            //settingViewModel.logout(user)
                            appModel.currentUser.postValue(user)
                            SharedPreferencesUtil.put(API.LOGIN_USER_TOKEN, "")
                            SharedPreferencesUtil.put(API.LOGIN_NICK_NAME, "")
                            SharedPreferencesUtil.put(API.LOGIN_ACTUL_NAME, "")
                            SharedPreferencesUtil.put(API.LOGIN_USER_ICON, "")
                            SharedPreferencesUtil.put(API.LOGIN_USER_PHONE, "")
                            SharedPreferencesUtil.put(API.LOGIN_USER_STATE, 0)
                            onBackPressed()
                        }
                    }
                }
            }
        }


    }


}