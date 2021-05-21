package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.databinding.FragmentCardFastInBinding
import com.puxiansheng.www.databinding.FragmentCardFastOutBinding
import com.puxiansheng.www.databinding.FragmentCardReleaseBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.mine.setting.UserSettingActivity
import com.puxiansheng.www.ui.release.fasttransfer.SimpleOrderViewModel
import kotlinx.coroutines.launch

class FastMyProgressFragment : Fragment() {
    private lateinit var viewModel: SimpleOrderViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[SimpleOrderViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentCardReleaseBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
            layoutNoLogin.visibility = View.GONE
            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount().let { list ->
                    if (list?.size!! > 0) {
                        layoutNoRelease.visibility = View.GONE
                        releaseList.visibility = View.VISIBLE
                    }else{
                        layoutNoRelease.visibility = View.VISIBLE
                        releaseList.visibility = View.GONE
                    }
                }
            }
        } else {
            layoutNoLogin.visibility = View.VISIBLE
            layoutNoRelease.visibility = View.GONE
        }


        btLogin.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }

        btRelease.setOnClickListener {
            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount().let { list ->
                    if (list?.size!! > 0) {
                        layoutNoRelease.visibility = View.GONE
                        releaseList.visibility = View.VISIBLE
                    }else{
                        layoutNoRelease.visibility = View.VISIBLE
                        releaseList.visibility = View.GONE
                    }
                }
            }
        }

    }.root
}