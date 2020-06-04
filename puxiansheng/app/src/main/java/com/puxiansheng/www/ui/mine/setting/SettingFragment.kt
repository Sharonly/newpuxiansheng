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
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentSettingBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.launch

class SettingFragment:AppFragment() {
    private lateinit var appModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        settingViewModel = ViewModelProvider(requireActivity())[SettingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentSettingBinding.inflate(inflater).apply {
        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).popBackStack()
        }

        lifecycleScope.launch {
            settingViewModel.getConfigInfo("about_us_url")?.let {
                configInfo ->
                userCompany.setOnClickListener {
                    val intent = Intent(requireActivity(), AboutUsActivity::class.java)
                    intent.putExtra("url", configInfo)
                    startActivity(intent)
                }
            }
        }

        userPhoneNum.text = SharedPreferencesUtil.get(API.LOGIN_USER_PHONE,"").toString()
        userPhoneNum.setOnClickListener {
            val intent = Intent(requireActivity(), UserSettingActivity::class.java)
            startActivity(intent)
        }
        changePassword.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                .navigate(R.id.action_settingFragment_to_resetPasswordFragment)
        }

//        userSuggest.setOnClickListener {
//            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
//                .navigate(R.id.action_settingFragment_to_userSuggestFragment)
//        }



        var myAppInfo = context?.applicationContext?.packageManager?.getPackageInfo(context?.packageName, 0)
        currenVersion.text =   myAppInfo?.versionName


        logout.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.logout()?.let {
                    if (it.code == API.CODE_SUCCESS) {
                        appModel.currentUser.value?.copy(loginState = 0)?.let { user ->
                            //settingViewModel.logout(user)
                            appModel.currentUser.postValue(user)
                            SharedPreferencesUtil.put(API.LOGIN_USER_TOKEN,"")
                            SharedPreferencesUtil.put(API.LOGIN_NICK_NAME,"")
                            SharedPreferencesUtil.put(API.LOGIN_ACTUL_NAME,"")
                            SharedPreferencesUtil.put(API.LOGIN_USER_ICON,"")
                            SharedPreferencesUtil.put(API.LOGIN_USER_PHONE,"")
                            SharedPreferencesUtil.put(API.LOGIN_USER_STATE,0)
                            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                                .navigateUp()
                        }
                    }
                }
            }
        }


        appModel.currentUser.observe(viewLifecycleOwner, Observer {
            it?.let { user ->
//                userAccount.text = user.userName
//                phoneNumber.text = user.userPhoneNumber
            }
        })
    }.root
}