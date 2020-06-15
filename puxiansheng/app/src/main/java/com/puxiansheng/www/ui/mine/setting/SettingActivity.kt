package com.puxiansheng.www.ui.mine.setting

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.launch

class SettingActivity : MyBaseActivity() {
    //    private lateinit var appModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
    }

    override fun business() {
//        appModel = ViewModelProvider(this)[MainViewModel::class.java]
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
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        change_permission.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_VIEW)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9) {
                mIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS";
                mIntent.data = Uri.fromParts("package", this.packageName, null);
            } else if (Build.VERSION.SDK_INT <= 8) {
                mIntent.action = Intent.ACTION_VIEW
                mIntent.setClassName(
                    "com.android.settings",
                    "com.android.setting.InstalledAppDetails"
                );
                mIntent.putExtra(
                    "com.android.settings.ApplicationPkgName",
                    this.packageName
                )
            }
            startActivity(mIntent);
        }

//        userSuggest.setOnClickListener {
//            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
//                .navigate(R.id.action_settingFragment_to_userSuggestFragment)
//        }


        var myAppInfo =
            this?.applicationContext?.packageManager?.getPackageInfo(this?.packageName, 0)
        curren_version.text = myAppInfo?.versionName


        logout.setOnClickListener {
            Log.d("---logout ", "  ")
            lifecycleScope.launch {
                settingViewModel.logout()?.let {
                    if (it.code == API.CODE_SUCCESS) {
                        Log.d("---logout ", " CODE_SUCCESS ")
//                        appModel.currentUser.value?.copy(loginState = 0)?.let { user ->
                        //settingViewModel.logout(user)
//                            appModel.currentUser.postValue(user)
                        var  user = User("","","","")
                        user?.loginState == 0
                        LiveDataBus.get().with("user")?.value =user
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