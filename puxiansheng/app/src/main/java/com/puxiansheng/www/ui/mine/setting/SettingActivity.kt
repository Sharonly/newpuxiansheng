package com.puxiansheng.www.ui.mine.setting

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.www.common.PermissionPageUtils
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.main.dialog.UpgradeDialog
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.coroutines.launch

class SettingActivity : MyBaseActivity() {
    private lateinit var appModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel
    private var isUpDialogShow: Boolean = false
    private var currentTokenCode: Int = 0

    private var permissionUtil: PermissionPageUtils? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
    }

    override fun business() {
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        settingViewModel = ViewModelProvider(this)[SettingViewModel::class.java]
//        permissionUtil = PermissionPageUtils(this@SettingActivity)
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


//            settingViewModel.getConfigInfo("privacy_url")?.let { configInfo ->
//                name_consult.setOnClickListener {
//                    val intent = Intent(this@SettingActivity, WebViewActivity::class.java)
//                    intent.putExtra("url", configInfo)
//                    startActivity(intent)
//                }
//            }
        }

        appModel?.currentSignatureTokenCode?.observe(this@SettingActivity, Observer { code ->
            currentTokenCode = code
        })

        appModel?.currentNewPackage?.observe(this, Observer {
            it?.let {
                when (currentTokenCode) {
                    API.CODE_SUCCESS -> {
                        Log.d("version","--- newVersion = "+it.newVersion)
                        if (it.newVersion == 1) {
                            if(!isUpDialogShow) {
                                UpgradeDialog(
                                    this,
                                    onClick = {},
                                    versionName = it.showVersion,
                                    fileDownUrl = it.downloadUrl,
                                    upgadeTips = it.tipsMsg,
                                    upgradeType = it.newPackage, isForceUpgrade = false
                                ).show(
                                    supportFragmentManager,
                                    UpgradeDialog::class.java.name
                                )
                                isUpDialogShow = true
                            }
                        }else{
                            Toast.makeText(this,"当前为最新版本",Toast.LENGTH_SHORT).show()
                        }
                    }

                    API.CODE_BANNED_VERSION -> {
                        if (!isUpDialogShow && it.newVersion == 1) {
                            UpgradeDialog(
                                this,
                                onClick = {
                                    finish()
                                },
                                versionName = it.showVersion,
                                fileDownUrl = it.downloadUrl,
                                upgadeTips = it.tipsMsg,
                                upgradeType = it.newPackage, isForceUpgrade = true
                            ).show(
                                supportFragmentManager,
                                UpgradeDialog::class.java.name
                            )
                            isUpDialogShow = true
                        }
                    }
                }
            }
        })

        curren_version.setOnClickListener {
            appModel?.requireLocalDevice()?.observe(this, Observer {
                it?.let {
                    lifecycleScope.launch {
                        appModel?.getSignatureVersion(
                            it,
                            SharedPreferencesUtil.get("registration_id", "") as String
                        )
                    }
                } ?: appModel?.requireDevice()
            })

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
//            permissionUtil?.jumpPermissionPage()
            changePermission()
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
                        var user = User("", "", "", "")
                        user?.loginState == 0
                        LiveDataBus.get().with("user")?.value = user
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

    fun changePermission() {
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
        startActivity(mIntent)
    }



}