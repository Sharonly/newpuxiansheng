package com.puxiansheng.www.ui.mine

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.www.R
import com.puxiansheng.www.common.*
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentMineBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.info.WebViewActivity
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.mine.history.MyHistoryActivity
import com.puxiansheng.www.ui.mine.favor.MyfarvorActivity
import com.puxiansheng.www.ui.mine.relase.*
import com.puxiansheng.www.ui.mine.setting.SettingActivity
import com.puxiansheng.www.ui.mine.setting.UserSettingActivity
import com.puxiansheng.www.ui.mine.suggest.UserSuggestActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.user_icon
import kotlinx.coroutines.launch

class MineFragment : AppFragment() {
    private lateinit var mineViewModel: MineViewModel
    private lateinit var appModel: MainViewModel
    var isLoading = false
    private var isLogin = false
    var phone = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mineViewModel = ViewModelProvider(requireActivity())[MineViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }


    //TODO 思路1： 将onCreateView中的请求网络转移到下面2个方法中，可以保证此fragment每次可见都是最新的,

    //TODO 思路2:  在其它页面通过livedatabus通知此fragment刷新，没试过不保证没有问题

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            lifecycleScope.launch {
                mineViewModel.getUserInformationFromRemote()?.let {
                    if (it is User) {
                        user_icon.urlCircleImg(it.icon)
                        user_account.text = it.nickName ?: it.actualName
                        user_phone.visibility = View.VISIBLE
                        SpUtils.put(API.LOGIN_ACTUL_PHONE, it.userPhoneNumber)
                        phone =it.userPhoneNumber
                        phone = phone.substring(0, 3) + "****" + phone.substring(7, 11)
                        user_phone.text = phone
                    } else {
                        user_account.text = "请登录"
                        user_phone.visibility = View.INVISIBLE
                        user_icon.setImageResource(R.mipmap.ic_default_icon)
                    }
                }

                mineViewModel.getReleaseCount()?.let {
                    public_data.text = it.releaseData.toString()
                    processing_data.text = it.processingData.toString()
                    finish_data.text = it.finishData.toString()
                }

                mineViewModel.requestBannerImage("api_user_conter_image")?.let { banners ->
//                    banner.urlBg(banners.imageUrl)
                    banner.urlBg(banners[0].imageUrl)

                    banner.setOnClickListener {
                        JumpUtils.pictureIntent(requireActivity(), banners[0])
                    }
                }


                mineViewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                    bt_my_kefu.setOnClickListener {
                        val intent = Intent(context, ServiceActivity::class.java)
                        intent.putExtra("title", "我的客服")
                        intent.putExtra("url", configInfo)
                        startActivity(intent)
                        MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "ServiceActivity")
                    }
                }

                mineViewModel.getConfigInfo("privacy_url")?.let { configInfo ->
                    privacy.setOnClickListener {
                        val intent = Intent(requireActivity(), WebViewActivity::class.java)
                        intent.putExtra("url", configInfo)
                        startActivity(intent)
                        MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "PrivacyActivity")
                    }
                }
            }

            //TODO  请求网络刷新页面
        }
    }


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            mineViewModel.getUserInformationFromRemote()?.let {
                if (it is User) {
                    Log.d("---imageicon", "UserInformation it.icon = " + it.icon)
                    user_icon.urlCircleImg(it.icon)
                    user_account.text = it.nickName ?: it.actualName
                    user_phone.visibility = View.VISIBLE
                    SpUtils.put(API.LOGIN_ACTUL_PHONE, it.userPhoneNumber)
                    phone =it.userPhoneNumber
                    phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
                    user_phone.text = phone
                } else {
                    user_account.text = "请登录"
                    user_phone.visibility = View.INVISIBLE
                    user_icon.setImageResource(R.mipmap.ic_default_icon)
                }
            }

            mineViewModel.getReleaseCount()?.let {
                public_data.text = it.releaseData.toString()
                processing_data.text = it.processingData.toString()
                finish_data.text = it.finishData.toString()
            }

            mineViewModel.requestBannerImage("api_user_conter_image")?.let { banners ->
                banner.urlBg(banners[0].imageUrl)
            }


            mineViewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                bt_my_kefu.setOnClickListener {
                    val intent = Intent(context, ServiceActivity::class.java)
                    intent.putExtra("title", "我的客服")
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "ServiceActivity")
                }
            }

            mineViewModel.getConfigInfo("privacy_url")?.let { configInfo ->
                privacy.setOnClickListener {
                    val intent = Intent(requireActivity(), WebViewActivity::class.java)
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                    MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "PrivacyActivity")
                }
            }
        }
//        MobclickAgent.onPageStart("MineFragment")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMineBinding.inflate(inflater).apply {

//        if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
//            isLogin = true
//            userAccount.text = get(API.LOGIN_NICK_NAME, "").toString()
//            userPhone.text = get(API.LOGIN_USER_PHONE, "").toString()
//            userIcon.urlIcon(get(API.LOGIN_USER_ICON, "").toString())
//        } else {
//            userAccount.text = "请登录"
//            userPhone.text = ""
//        }

        userIcon.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "userIconClick")
            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                val intent = Intent(requireActivity(), UserSettingActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        userAccount.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }


        iconSetting.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "setIconClick")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
//                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
//                    R.id.action_mainFragment_to_settingFragment
//                )

                val intent = Intent(requireActivity(), SettingActivity::class.java)
                startActivity(intent)
            }
        }
        btMyRelease.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "MyReleaseAllActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), MyReleaseAllActivity::class.java)
                startActivity(intent)
            }
        }
        btMyFarvior.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "MyfarvorActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), MyfarvorActivity::class.java)
                startActivity(intent)
            }
        }
        btMyHistory.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "MyHistoryActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), MyHistoryActivity::class.java)
                startActivity(intent)
            }
        }

        layoutPublic.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "UserOrderPublicActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), UserOrderPublicActivity::class.java)
                startActivity(intent)
            }
        }
        layoutProcessing.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "UserOrderProcessingActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), UserOrderProcessingActivity::class.java)
                startActivity(intent)
            }
        }

        layoutSoldOut.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "UserOrderSoldOutActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), UserOrderSoldOutActivity::class.java)
                startActivity(intent)
            }
        }


        //TODO 2020/6/8
        btRequest.setOnClickListener {
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "UserSuggestActivity")
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                val intent = Intent(requireActivity(), UserSuggestActivity::class.java)
                startActivity(intent)
            }
        }

        phoneNum.setOnClickListener {
            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${phoneNum.text}")
                startActivity(this)
            }
        }

        messageManager.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_VIEW)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9) {
                mIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS";
                mIntent.data = Uri.fromParts("package", requireActivity().packageName, null);
            } else if (Build.VERSION.SDK_INT <= 8) {
                mIntent.action = Intent.ACTION_VIEW;
                mIntent.setClassName(
                    "com.android.settings",
                    "com.android.setting.InstalledAppDetails"
                );
                mIntent.putExtra(
                    "com.android.settings.ApplicationPkgName",
                    requireActivity().packageName
                );
            }
            context?.startActivity(mIntent);
        }

        appModel.currentUser?.observe(requireActivity(), Observer { user ->
            user?.let {
                if (it.isLogin) {
                    isLogin = true
                    userAccount?.text = user.name ?: user.nickName
                    userPhone?.visibility = View.VISIBLE
                    phone =it.userPhoneNumber
                    phone = phone.substring(0, 3) + "****" + phone.substring(7, 11)
                    userPhone?.text = phone
                    if (user.icon.isNotEmpty()) {
                        userIcon?.urlCircleImg(user.icon)
                    }
                } else {
                    isLogin = false
                    userAccount?.text = "请登录"
                    userPhone?.visibility = View.INVISIBLE
                    userIcon?.urlCircleImg(R.mipmap.ic_default_icon)
                }
            }
        })
    }.root



//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("MineFragment")
//    }
}