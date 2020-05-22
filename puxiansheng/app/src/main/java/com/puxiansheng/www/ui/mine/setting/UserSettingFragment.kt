package com.puxiansheng.www.ui.mine.setting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentMySettingBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.launch


class UserSettingFragment : AppFragment() {
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
    ): View? = FragmentMySettingBinding.inflate(inflater).apply {
        lifecycleScope.launch {
            settingViewModel.getUserInformationFromRemote()?.let {
                if (it is User) {
                    userIcon.urlIcon(it.icon)
                    inputNickName.setText(it.nickname)
                    inputActualName.setText(it.actualName)
                    if(it.userSex == 2){
                        femle.isChecked = false
                    }else{
                        male.isChecked = true
                    }
                }
            }
        }

        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).popBackStack()
        }

        userIcon.setOnClickListener {
            ChangeIconDialog().show(childFragmentManager, ChangeIconDialog::class.java.name)
        }

        inputNickName.setText(SharedPreferencesUtil.get(API.LOGIN_NICK_NAME, "").toString())
        inputActualName.setText(SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString())


        inputNickName.addTextChangedListener {
            settingViewModel.nickName = it.toString()
        }
        inputActualName.addTextChangedListener {
            settingViewModel.actualName = it.toString()
        }


        if (SharedPreferencesUtil.get(API.USER_SEX, 0) == 0) {
            male.isChecked = true
        } else {
            femle.isChecked = true
        }
        val drawableSelected =
            resources.getDrawable(R.mipmap.ic_sex_selected)
        val drawableNoSelect =
            resources.getDrawable(R.drawable.ic_sex_no_select)
        rgSex.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.male) {
                Log.d("----sex--", " 男")
                male.setCompoundDrawables(drawableSelected, null, null, null)
                femle.setCompoundDrawables(drawableNoSelect, null, null, null)
                settingViewModel.sex = 0
            } else {
                Log.d("----sex--", " 女")
                male.setCompoundDrawables(drawableNoSelect, null, null, null)
                femle.setCompoundDrawables(drawableSelected, null, null, null)
                settingViewModel.sex = 1
            }

        }
//        male.setOnClickListener {
//            male.setCompoundDrawables(drawableSelected, null, null, null)
//            femle.setCompoundDrawables(drawableNoSelect, null, null, null)
//            settingViewModel.sex = 0
//        }
//
//        femle.setOnClickListener {
//            male.setCompoundDrawables(drawableNoSelect, null, null, null)
//            femle.setCompoundDrawables(drawableSelected, null, null, null)
//            settingViewModel.sex = 1
//        }

        userPhone.setText(SharedPreferencesUtil.get(API.LOGIN_USER_PHONE, "").toString())

        userLocation.text = SharedPreferencesUtil.get(API.USER_CITY_NAME, "").toString()
        userLocation.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                .navigate(R.id.action_settingFragment_to_locationFragment)
        }

        btSave.setOnClickListener {
            lifecycleScope.launch {
                settingViewModel.submitUserInfo()?.let {
                    if (it.code == API.CODE_SUCCESS) {

                        SharedPreferencesUtil.put(API.LOGIN_USER_ICON, "")
                        settingViewModel.nickName?.let { it1 ->
                            SharedPreferencesUtil.put(
                                API.LOGIN_NICK_NAME,
                                it1
                            )
                        }
                        settingViewModel.actualName?.let { it1 ->
                            SharedPreferencesUtil.put(
                                API.LOGIN_ACTUL_NAME,
                                it1
                            )
                        }
                        settingViewModel.sex?.let { it1 ->
                            SharedPreferencesUtil.put(
                                API.USER_SEX,
                                it1
                            )
                        }
                        settingViewModel.contactPhone?.let { it1 ->
                            SharedPreferencesUtil.put(
                                API.LOGIN_USER_PHONE,
                                it1
                            )
                        }
                        settingViewModel.cityId?.let { it1 ->
                            SharedPreferencesUtil.put(
                                API.USER_CITY_ID,
                                it1
                            )
                        }
                        SharedPreferencesUtil.put(API.USER_CITY_NAME, userLocation.text)
                        Toast.makeText(requireActivity(), "保存成功", Toast.LENGTH_SHORT)
                        Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                            .popBackStack()
                    }
                }
            }
        }



        appModel.currentCity.observe(requireActivity(), Observer {
            userLocation.text = it.text
        })


    }.root

}