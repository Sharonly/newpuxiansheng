package com.puxiansheng.www.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyActivityManage
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.login.WechatAPI
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.home.NewHomeFragment
import com.puxiansheng.www.ui.info.InfoHomeListFragment
import com.puxiansheng.www.ui.main.dialog.AdvertmentDialog
import com.puxiansheng.www.ui.main.dialog.UpgradeDialog
import com.puxiansheng.www.ui.message.MessageHomeListFragment
import com.puxiansheng.www.ui.mine.MineFragment
import com.puxiansheng.www.ui.release.ReleaseFragment
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_new_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeActivity : MyBaseActivity() {
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
    private var currentTokenCode: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_new_home
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun business() {
        getSharedPreferences("pxs_privacy", Context.MODE_PRIVATE).let {
            it.getInt("show_privacy", 0).let { isShow ->
                if (isShow == 0) {
                    val intent = Intent(this, StartActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            requestCodePermissions
        )
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        initFragment()
        initData()

    }

    private fun initData() {
        var isLogin = false
        var isUpDialogShow = false
        var isAdvertDialogShow = false
        appModel?.saveLoginUser()?.let {
            appModel?.currentUser?.postValue(it)
            API.setAuthToken(it.token)
        }

        LiveDataBus.get().with("currentCity", LocationNode::class.java)?.observe(this, Observer {
            appModel?.currentCity?.postValue(it)
            API.setCityId(it.nodeID.toString())
        })

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


        appModel?.currentAdvert?.observe(this, Observer {
            it?.let {
                if (it == 1) {
                    lifecycleScope.launch {
                        appModel?.requestAdvertImages("api_index_pop_up_ads")?.let { data ->
                            if (data.code == API.CODE_SUCCESS) {
                                if (!isAdvertDialogShow && data?.data?.banners?.isNotEmpty()!!) {
                                    var dialog = AdvertmentDialog(
                                        context = this@HomeActivity,
                                        baners = data?.data?.banners!!
                                    )
                                    dialog.show(
                                        supportFragmentManager,
                                        AdvertmentDialog::class.java.name
                                    )
                                    isAdvertDialogShow = true
                                    dialog.listener = object : AdvertmentDialog.OnDissListener {
                                        override fun onDiss() {
                                            isAdvertDialogShow = false
                                        }
                                    }
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
//                           UpgradeDialog(
//                               this@HomeActivity,
//                               onClick = {},
//                               versionName = it.showVersion,
//                               fileDownUrl = it.downloadUrl,
//                               upgadeTips = it.tipsMsg,
//                               upgradeType = it.newPackage, isForceUpgrade = false
//                           ).show(
//                               supportFragmentManager,
//                               UpgradeDialog::class.java.name
//                           )

                            UpgradeDialog.getInstance().setData(
                                this, versionName = it.showVersion,
                                fileDownUrl = it.downloadUrl,
                                upgadeTips = it.tipsMsg,
                                type = it.newPackage, isUpgrade = true
                            )
                            UpgradeDialog.getInstance().show(
                                supportFragmentManager,
                                UpgradeDialog::class.java.name
                            )
                            isUpDialogShow = true
                        }
                    }

                    API.CODE_BANNED_VERSION -> {
                        if (!isUpDialogShow && it.newVersion == 1) {
//                           UpgradeDialog(
//                               this@HomeActivity,
//                               onClick = {
//                                   finish()
//                               },
//                               versionName = it.showVersion,
//                               fileDownUrl = it.downloadUrl,
//                               upgadeTips = it.tipsMsg,
//                               upgradeType = it.newPackage, isForceUpgrade = true
//                           ).show(
//                               supportFragmentManager,
//                               UpgradeDialog::class.java.name
//                           )
                            UpgradeDialog.getInstance().setData(
                                this, versionName = it.showVersion,
                                fileDownUrl = it.downloadUrl,
                                upgadeTips = it.tipsMsg,
                                type = it.newPackage, isUpgrade = true
                            )
                            UpgradeDialog.getInstance().show(
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
                Log.d("GET_TOKEN----", "requireLocalDevice  111---- ")
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
        super.onDestroy()
        finish()
    }

    override fun onResume() {
        super.onResume()
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


    private fun initFragment() {
        val homeFragment: Fragment = NewHomeFragment()
        val infoHomeFragment: Fragment = InfoHomeListFragment()
        val releaseFragment: Fragment = ReleaseFragment()
        val mineFragment: Fragment = MineFragment()
        val messageHomeFragment: Fragment = MessageHomeListFragment()
        val fragments = listOf(
            homeFragment,
            infoHomeFragment,
            releaseFragment,
            messageHomeFragment,
            mineFragment
        )
        //初始化显示的Fragment(外层的)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.home_container, fragments[0])
            .add(R.id.home_container, fragments[1])
            .add(R.id.home_container, fragments[2])
            .add(R.id.home_container, fragments[3])
            .add(R.id.home_container, fragments[4])
            .show(fragments[0])
            .hide(fragments[1])
            .hide(fragments[2])
            .hide(fragments[3])
            .hide(fragments[4])
            .commitAllowingStateLoss()

//        homeFragment = HomeFragment()
//        addFragment(homeFragment!!)
//        showFragment(homeFragment!!)

        appModel?.lastFragment = homeFragment

        radio_group_button.setOnCheckedChangeListener { group, checkedId ->
            Log.d("---homeactivity", " appModel?.lastFragment = " + appModel?.lastFragment)
            if (appModel?.lastFragment == null) {
                finish()
            }
            when (checkedId) {
                R.id.navigation_home -> {
                    if (appModel?.lastFragment !is NewHomeFragment) {
                        appModel?.lastFragment?.let {
                            supportFragmentManager.beginTransaction().hide(it)
                                .show(homeFragment).commitAllowingStateLoss()
                        }
                        appModel?.lastFragment = homeFragment
                        MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "HomeFragment")
                    }
                }

                R.id.navigation_info -> {
                    if (appModel?.lastFragment !is InfoHomeListFragment) {
                        appModel?.lastFragment?.let {
                            supportFragmentManager.beginTransaction().hide(it)
                                .show(infoHomeFragment).commitAllowingStateLoss()
                        }
                        appModel?.lastFragment = infoHomeFragment
                        MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "InfoHomeListFragment")
                    }

//                    if (infoHomeFragment == null) {
//                        infoHomeFragment = InfoHomeListFragment()
//                    }
//                    addFragment(infoHomeFragment!!)
//                    showFragment(infoHomeFragment!!)
                }

                R.id.navigation_release -> {
                    if (appModel?.lastFragment !is ReleaseFragment) {
                        appModel?.lastFragment?.let {
                            supportFragmentManager.beginTransaction().hide(
                                it
                            ).show(releaseFragment).commitAllowingStateLoss()
                        }
                        appModel?.lastFragment = releaseFragment
                        MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "ReleaseFragment")
                    }
//                    if (releaseFragment == null) {
//                        releaseFragment = ReleaseFragment()
//                    }
//                    addFragment(releaseFragment!!)
//                    showFragment(releaseFragment!!)

                }
                R.id.navigation_message -> {
                    if (appModel?.lastFragment !is MessageHomeListFragment) {
                        appModel?.lastFragment?.let {
                            supportFragmentManager.beginTransaction().hide(it)
                                .show(
                                    messageHomeFragment
                                ).commitAllowingStateLoss()
                        }
                        appModel?.lastFragment = messageHomeFragment
                        MobclickAgent.onEvent(
                            this,
                            UMengKeys.PAGE_NAME,
                            "MessageHomeListFragment"
                        )
                    }

//                    if (messageHomeFragment == null) {
//                        messageHomeFragment = MessageHomeListFragment()
//                    }
//                    addFragment(messageHomeFragment!!)
//                    showFragment(messageHomeFragment!!)
                }

                R.id.navigation_mine -> {
                    if (appModel?.lastFragment !is MineFragment) {
                        appModel?.lastFragment?.let {
                            supportFragmentManager.beginTransaction().hide(it)
                                .show(mineFragment).commitAllowingStateLoss()
                        }
                        appModel?.lastFragment = mineFragment
                        MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "MineFragment")
                    }

//                    if (mineFragment == null) {
//                        mineFragment = MineFragment()
//                    }
//                    addFragment(mineFragment!!)
//                    showFragment(mineFragment!!)
                }
            }
        }
        // 保证第一次会回调OnCheckedChangeListener
        navigation_home?.isChecked = true
    }


    /*添加fragment*/
//    private fun addFragment(fragment :Fragment ) {
//        /*判断该fragment是否已经被添加过  如果没有被添加  则添加*/
//        if (!fragment.isAdded) {
//            supportFragmentManager.beginTransaction().add(R.id.home_container, fragment).commitAllowingStateLoss()
//            /*添加到 fragmentList*/
//            fragmentList.add(fragment);
//        }
//    }
//
//    /*显示fragment*/
//    private fun showFragment(fragment :Fragment) {
//        fragmentList.forEach { frag ->
//            if (frag != fragment) {
//                /*先隐藏其他fragment*/
//                supportFragmentManager.beginTransaction().hide(frag).commit();
//            }
//        }
//        supportFragmentManager.beginTransaction().show(fragment).commit();
//    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //销毁前activity数量
//            println("剩余activity---》${MyActivityManage.activityMap.size}")
            MyActivityManage.exitApp()
        }
        return super.onKeyDown(keyCode, event)
    }
}