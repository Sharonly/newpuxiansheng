package com.puxiansheng.www.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.login.WechatAPI
import com.puxiansheng.www.ui.home.HomeFragment
import com.puxiansheng.www.ui.info.InfoHomeListFragment
import com.puxiansheng.www.ui.main.dialog.AdvertmentDialog
import com.puxiansheng.www.ui.main.dialog.UpgradeDialog
import com.puxiansheng.www.ui.message.MessageHomeListFragment
import com.puxiansheng.www.ui.mine.MineFragment
import com.puxiansheng.www.ui.release.ReleaseFragment
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_new_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeActivity: MyBaseActivity() {
    var context: Context = this@HomeActivity
    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private var appModel: MainViewModel? = null
    private var isUpDialogShow: Boolean = false
    private var currentTokenCode: Int = 0
    private val homeFragment: Fragment = HomeFragment()
    private val infoHomeFragment: Fragment = InfoHomeListFragment()
    private val releaseFragment: Fragment = ReleaseFragment()
    private val mineFragment: Fragment = MineFragment()
    private var messageHomeFragment: Fragment = MessageHomeListFragment()
    private val fragments = listOf(homeFragment,infoHomeFragment,releaseFragment,messageHomeFragment,mineFragment)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        val stringExtra = intent?.getStringExtra("name")
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_new_home
    }

    override fun business() {
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        LiveDataBus.get().with("currentCity", LocationNode::class.java)?.observe(this, Observer {
            Log.d("---city--"," get city = "+it.nodeID.toString())
            appModel?.currentCity?.postValue(it)
            API.setCityId(it.nodeID.toString())
        })

        initView()

        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            requestCodePermissions
        )
        var isLogin = false

        appModel?.saveLoginUser()?.let {
            appModel?.currentUser?.postValue(it)
            API.setAuthToken(it.token)
        }

        LiveDataBus.get().with("user", User::class.java)
            ?.observe(this, Observer { user ->
                user?.let {
                    appModel?.currentUser?.postValue(user)
                }
            })

        appModel?.currentSignatureToken?.observe(this, Observer {
            it?.let {
                appModel?.getSelectiveMenuDataFromRemote()
                appModel?.getSystemConfigFromRemote()

                if (appModel?.currentCity?.value == null)
                    appModel?.getCurrentLocation()

                if (!isLogin) lifecycleScope.launch(Dispatchers.IO) {
                    SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "")?.let { it ->
                        appModel?.currentUser?.postValue(appModel?.saveLoginUser())
                        API.setAuthToken(it.toString())
                    }
                }
            }
        })


        appModel?.currentAdvert?.observe(this,Observer {
            it?.let {
                if(it == 1){
                    lifecycleScope.launch {
                        appModel?.requestAdvertImages("api_index_pop_up_ads")?.let {
                            if (it.code == API.CODE_SUCCESS) {
                                if (it?.data?.banners?.isNotEmpty()!!) {
                                    AdvertmentDialog(context = this@HomeActivity, baners = it?.data?.banners!!).show(
                                        supportFragmentManager,
                                        AdvertmentDialog::class.java.name
                                    )
                                }
                            }
                        }
                    }
                }
            }
        })


        appModel?.currentNewPackage?.observe(this, Observer {
            it?.let {
                when (currentTokenCode) {
                    API.CODE_SUCCESS -> {
                        if (!isUpDialogShow && it.newVersion == 1) {
                            UpgradeDialog(
                                this@HomeActivity,
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
                    }

                    API.CODE_BANNED_VERSION -> {
                        if (!isUpDialogShow && it.newVersion == 1) {
                            UpgradeDialog(
                                this@HomeActivity,
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


        appModel?.currentSignatureTokenCode?.observe(this@HomeActivity, Observer { code ->
            currentTokenCode = code
        })

        appModel?.requireLocalDevice()?.observe(this@HomeActivity, Observer {
            it?.let {
                appModel?.refreshSignatureToken(
                    it,
                    SharedPreferencesUtil.get("registration_id", "") as String
                )
            } ?: appModel?.requireDevice()
        })

        API.logoutSignal.observe(this, Observer {
            // Log.d("---logout"," it = "+it)
//            println("---logout1--->${it}")
            if (it == API.CODE_REQUIRE_LOGIN ||
                it == API.CODE_ERROR_AUTH_TOKEN ||
                it == API.CODE_AUTO_CODE_INVALID ||
                it == API.CODE_AUTO_CODE_EXPIRED ||
                it == API.CODE_AUTO_CODE_ERROR ||
                it == API.CODE_AUTO_CODE_EMPTY ||
                it == API.CODE_BANNED_USER
            ) {
                appModel?.forceLogout()
//                println("---logout2--->${it}")
                Toast.makeText(this, "当前登录已失效，请重新登录！", Toast.LENGTH_SHORT).show()
            }
        })
        applicationContext?.let {
            WechatAPI.instance = WXAPIFactory.createWXAPI(it, "wxe5266f2fb1236eee", true)
//            WechatAPI.instance = WXAPIFactory.createWXAPI(it, API.WEIXIN_APP_ID, true)
        }
    }


    override fun onDestroy() {
        appModel = null
        super.onDestroy()
    }


    private fun initView() {
        //初始化显示的Fragment(外层的)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.home_container,fragments[0])
            .add(R.id.home_container,fragments[1])
            .add(R.id.home_container,fragments[2])
            .add(R.id.home_container,fragments[3])
            .add(R.id.home_container,fragments[4])
            .show(fragments[0])
            .hide(fragments[1])
            .hide(fragments[2])
            .hide(fragments[3])
            .hide(fragments[4])
            .commit();
        appModel?.lastFragment = homeFragment
        radio_group_button.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            var mFragment: Fragment? = null
            override fun onCheckedChanged(group: RadioGroup, @IdRes checkedId: Int) {


                when (checkedId) {
                    R.id.navigation_home -> {
                        if (appModel?.lastFragment !is HomeFragment) {
                            appModel?.lastFragment?.let {
                                supportFragmentManager.beginTransaction().hide(it)
                                    .show(homeFragment).commit()
                            }
                            appModel?.lastFragment = homeFragment
                        }
                    }
                    R.id.navigation_info -> {
                        if (appModel?.lastFragment !is InfoHomeListFragment) {
                            appModel?.lastFragment?.let {
                                supportFragmentManager.beginTransaction().hide(it)
                                    .show(infoHomeFragment).commit()
                            }
                            appModel?.lastFragment = infoHomeFragment
                        }
                    }

                    R.id.navigation_release -> {
                        if (appModel?.lastFragment !is ReleaseFragment) {
                            appModel?.lastFragment?.let {
                                supportFragmentManager.beginTransaction().hide(
                                    it
                                ).show(releaseFragment).commit()
                            }
                            appModel?.lastFragment = releaseFragment

                        }
                    }
                    R.id.navigation_message -> {
                        if (appModel?.lastFragment !is MessageHomeListFragment) {
                            appModel?.lastFragment?.let {
                                supportFragmentManager.beginTransaction().hide(it)
                                    .show(
                                        messageHomeFragment
                                    ).commit()
                            }
                            appModel?.lastFragment = messageHomeFragment

                        }
                    }

                    R.id.navigation_mine -> {
                        if (appModel?.lastFragment !is MineFragment) {
                            appModel?.lastFragment?.let {
                                supportFragmentManager.beginTransaction().hide(it)
                                    .show(mineFragment).commit()
                            }
                            appModel?.lastFragment = mineFragment
                        }
                    }
                }
            }
        })
        // 保证第一次会回调OnCheckedChangeListener
        navigation_home?.isChecked = true
    }


}