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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.common.urlBg
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentMineBinding
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.mine.history.MyHistoryActivity
import com.puxiansheng.www.ui.mine.relase.MyReleaseAllActivity
import com.puxiansheng.www.ui.mine.favor.MyfarvorActivity
import com.puxiansheng.www.ui.mine.relase.OrderProcessingActivity
import com.puxiansheng.www.ui.mine.relase.OrderPublicActivity
import com.puxiansheng.www.ui.mine.relase.OrderSoldOutActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.mine.setting.SettingActivity
import com.puxiansheng.www.ui.mine.setting.UserSettingActivity
import com.puxiansheng.www.ui.mine.suggest.UserSuggestActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.user_icon
import kotlinx.android.synthetic.main.fragment_my_setting.*
import kotlinx.coroutines.launch

class MineFragment : Fragment() {
    private lateinit var mineViewModel: MineViewModel
    private lateinit var appModel: MainViewModel
    var isLoading = false
    private var isLogin = false

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
                        Log.d("---imageicon","UserInformation it.icon = "+it.icon)
                        user_icon.urlIcon(it.icon)
                        user_account.text = it.nickName ?: it.actualName
                        user_phone.visibility = View.VISIBLE
                        user_phone.text = get(API.LOGIN_USER_PHONE,"").toString()
                        SharedPreferencesUtil.put(API.LOGIN_ACTUL_PHONE, it.userPhoneNumber)
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
                    banner.urlBg(banners.imageUrl)
                }


                mineViewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                    bt_my_kefu.setOnClickListener {
                        val intent = Intent(context, InfoDetailActivity::class.java)
                        intent.putExtra("url", configInfo)
                        startActivity(intent)
                    }
                }

                mineViewModel.getConfigInfo("privacy_url")?.let { configInfo ->
                    privacy.setOnClickListener {
                        val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
                        intent.putExtra("url", configInfo)
                        startActivity(intent)
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
                    Log.d("---imageicon","UserInformation it.icon = "+it.icon)
                    user_icon.urlIcon(it.icon)
                    user_account.text = it.nickName ?: it.actualName
                    user_phone.visibility = View.VISIBLE
                    user_phone.text = get(API.LOGIN_USER_PHONE,"").toString()
                    SharedPreferencesUtil.put(API.LOGIN_ACTUL_PHONE, it.userPhoneNumber)
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
                banner.urlBg(banners.imageUrl)
            }


            mineViewModel.getConfigInfo("api_kf_url")?.let { configInfo ->
                bt_my_kefu.setOnClickListener {
                    val intent = Intent(context, ServiceActivity::class.java)
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                }
            }

            mineViewModel.getConfigInfo("privacy_url")?.let { configInfo ->
                privacy.setOnClickListener {
                    val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                }
            }


        }


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
            if (get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                val intent = Intent(requireActivity(), UserSettingActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        userAccount.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }


        iconSetting.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
//                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
//                    R.id.action_mainFragment_to_settingFragment
//                )

                val intent = Intent(requireActivity(), SettingActivity::class.java)
                startActivity(intent)
            }
        }
        btMyRelease.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyReleaseAllActivity::class.java)
                startActivity(intent)
            }
        }
        btMyFarvior.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyfarvorActivity::class.java)
                startActivity(intent)
            }
        }
        btMyHistory.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), MyHistoryActivity::class.java)
                startActivity(intent)
            }
        }

        layoutPublic.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderPublicActivity::class.java)
                startActivity(intent)
            }
        }
        layoutProcessing.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderProcessingActivity::class.java)
                startActivity(intent)
            }
        }

        layoutSoldOut.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), OrderSoldOutActivity::class.java)
                startActivity(intent)
            }
        }


        //TODO 2020/6/8
        btRequest.setOnClickListener {
            if (!isLogin) {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(requireActivity(), UserSuggestActivity::class.java)
                startActivity(intent)
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
                    userAccount.text = user.name ?: user.nickName
                    userPhone.visibility = View.VISIBLE
                    userPhone.text = get(API.LOGIN_USER_PHONE,"").toString()
                    SharedPreferencesUtil.put(API.LOGIN_ACTUL_PHONE, it.userPhoneNumber)
                    Log.d("---imageicon"," user.icon = "+user.icon)
                    if (user.icon.isNotEmpty()) {
                        userIcon.urlIcon(user.icon)
                    }
                } else {
                    isLogin = false
                    userAccount.text = "请登录"
                    userPhone.visibility = View.INVISIBLE
                    user_icon.setImageResource(R.mipmap.ic_default_icon)
                }
            }
        })


    }.root


}