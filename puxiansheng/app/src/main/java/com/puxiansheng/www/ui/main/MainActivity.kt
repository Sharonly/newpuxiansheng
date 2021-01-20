package com.puxiansheng.www.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.PermissionUtils
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyActivityManage
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.AndroidBug5497Workaround
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
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : MyBaseActivity() {
    var context: Context = this@MainActivity
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
    private var lastIndex = -1
    private var backCount = 0

    private val fragmentsArray = arrayListOf<Fragment>(
        NewHomeFragment(),
        InfoHomeListFragment(),
        ReleaseFragment(),
        MessageHomeListFragment(),
        MineFragment()
    )


    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this, true, R.color.color81, true)
        return R.layout.activity_home
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun business() {
        if (!isTaskRoot) {
            val intentAction = intent.action
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction == Intent.ACTION_MAIN) {
                finish()
                return
            }
        }

//        AndroidBug5497Workaround.assistActivity(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN and WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
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

//        ActivityCompat.requestPermissions(
//            this,
//            requiredPermissions,
//            requestCodePermissions
//        )

        PermissionUtils.requestPermission(this@MainActivity, requiredPermissions)
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        initBottomBar()
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
                                        context = this@MainActivity,
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


        appModel?.currentSignatureTokenCode?.observe(this@MainActivity, Observer { code ->
            currentTokenCode = code
        })

        appModel?.requireLocalDevice()?.observe(this@MainActivity, Observer {
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


    private fun initBottomBar() {
//        val menuView: BottomNavigationMenuView = nav_view.getChildAt(0) as BottomNavigationMenuView
//        val itemView: BottomNavigationItemView = menuView.getChildAt(3) as BottomNavigationItemView
//        val badge: View = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false);
//        itemView.addView(badge)
        nav_view.itemIconTintList = null
        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    swichFragment(0, false)
                    MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "HomeFragment")
                    //  MyScreenUtil.setStateBarStyle(this@MainActivity, false, R.color.white, true)
                }
                R.id.navigation_info -> {
                    swichFragment(1, false)
                    MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "InfoHomeListFragment")
                    //  MyScreenUtil.setStateBarStyle(this@MainActivity, false, R.color.white, true)
                }
                R.id.navigation_release -> {
                    swichFragment(2, false)
                    MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "ReleaseFragment")
                    // MyScreenUtil.setStateBarStyle(this@MainActivity, false, R.color.white, true)
                }

                R.id.navigation_message -> {
                    swichFragment(3, false)
                    MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "MessageHomeListFragment")
                    // MyScreenUtil.setStateBarStyle(this@MainActivity, false, R.color.white, true)
                }
                R.id.navigation_mine -> {
                    swichFragment(4, false)
                    MobclickAgent.onEvent(this, UMengKeys.PAGE_NAME, "MineFragment")
                    // MyScreenUtil.setStateBarStyle(this@MainActivity, true, R.color.white, true)
                }
            }
            true
        }

        nav_view.selectedItemId = R.id.navigation_home

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("activity", "  main onNewIntent ")
        val intExtra = intent?.getIntExtra("index", lastIndex) ?: lastIndex

        //swichFragment(intExtra,true)
        if (fragmentsArray[intExtra].isAdded) {
            swichFragment(intExtra, true)
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_container, fragmentsArray[intExtra])
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss()
            lastIndex = intExtra
        }
        if (intExtra == 0) {
            nav_view.selectedItemId = R.id.navigation_home
        } else if (intExtra == 1) {
            nav_view.selectedItemId = R.id.navigation_info
        } else if (intExtra == 2) {
            nav_view.selectedItemId = R.id.navigation_release
        } else if (intExtra == 3) {
            nav_view.selectedItemId = R.id.navigation_message
        } else if (intExtra == 4) {
            nav_view.selectedItemId = R.id.navigation_mine
        }
    }


    /**
     * 切换fragment
     */
    private fun swichFragment(position: Int, isJump: Boolean) {
        if (lastIndex == position) {
            return
        }

        if (fragmentsArray[position].isAdded) {
            supportFragmentManager.beginTransaction().show(fragmentsArray[position])
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .hide(fragmentsArray[lastIndex]).commitAllowingStateLoss()
        } else {
            if (lastIndex != -1) {
                if (isJump) {
                    supportFragmentManager.beginTransaction().show(fragmentsArray[position])
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .hide(fragmentsArray[lastIndex]).commitAllowingStateLoss()
                } else {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.home_container, fragmentsArray[position])
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .hide(fragmentsArray[lastIndex]).commitAllowingStateLoss()
                }
            } else {
                supportFragmentManager.beginTransaction()
                    .add(R.id.home_container, fragmentsArray[position])
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commitAllowingStateLoss()
            }
        }
        lastIndex = position
    }


    fun gotoRelease() {
        lastIndex = 0
//        swichFragment(2)
        nav_view.selectedItemId = R.id.navigation_release
    }


    fun gotoInfo() {
//        lastIndex = 0
//        swichFragment(1,true)
//        nav_view.selectedItemId = R.id.navigation_home
//        nav_view.selectedItemId = nav_view.getChildAt(1).findViewById<>(R.)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            //销毁前activity数量
//            println("剩余activity---》${MyActivityManage.activityMap.size}")
            Log.e("---activity", " exitApp")
            MyActivityManage.exitApp()
        }
        return super.onKeyDown(keyCode, event)
    }
}