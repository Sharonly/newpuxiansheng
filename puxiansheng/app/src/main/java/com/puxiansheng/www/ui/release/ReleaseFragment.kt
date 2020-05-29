package com.puxiansheng.www.ui.release

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentReleaseBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferInOrdersActivity

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

class ReleaseFragment : AppFragment() {
    private lateinit var releaseViewModel: ReleaseViewModel
    private lateinit var appModel: MainViewModel
    var name = ""
    var phone = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        releaseViewModel = ViewModelProvider(this)[ReleaseViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentReleaseBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        fastRelease.setOnClickListener {

        }

        transferOut.setOnClickListener {
            name = SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString()
            phone = SharedPreferencesUtil.get(API.LOGIN_USER_PHONE, "").toString()

            if (SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    val intent =
                        Intent(
                            requireActivity(),
                            InsertOrUpdateTransferOutOrderActivity::class.java
                        )
                    startActivity(intent)
                } else {
                    Toast.makeText(requireActivity(), "需要先保存个人信息才能发布哟", Toast.LENGTH_SHORT)
                    Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                        .navigate(R.id.action_mainFragment_to_userSettingFragment)
                }
            } else {
                val intent =
                    Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
        transferIn.setOnClickListener {
            name = SharedPreferencesUtil.get(API.LOGIN_ACTUL_NAME, "").toString()
            phone = SharedPreferencesUtil.get(API.LOGIN_USER_PHONE, "").toString()

            if (SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {

                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    val intent =
                        Intent(requireActivity(), InsertOrUpdateTransferInOrderActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireActivity(), "需要先保存个人信息才能发布哟", Toast.LENGTH_SHORT)
                    Navigation.findNavController(requireActivity(), R.id.homeNavHost)
                        .navigate(R.id.action_mainFragment_to_userSettingFragment)
                }


            } else {
                val intent =
                    Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }

        }


    }.root
}