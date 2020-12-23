package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.ext.toast
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import com.puxiansheng.www.databinding.FragmentCardFastInBinding
import com.puxiansheng.www.databinding.FragmentCardFastOutBinding
import com.puxiansheng.www.databinding.FragmentCardReleaseBinding
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.mine.relase.ReleaseInAdapter
import com.puxiansheng.www.ui.mine.setting.UserSettingActivity
import com.puxiansheng.www.ui.release.fasttransfer.SimpleOrderViewModel
import kotlinx.coroutines.launch

class FastMyReleaseFragment : Fragment() {
    private lateinit var viewModel: SimpleOrderViewModel
    var adapter:ReleaseProgressAdapter?=null

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

        val linearLayoutManager =
            LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        releaseList.layoutManager = linearLayoutManager
        val width = (MyScreenUtil.getScreenWidth(requireContext()) - MyScreenUtil.dip2px(requireContext(), 54f))//计算滚动宽度=屏幕-左右各15-右边24
        releaseList.scrollTo(width,0)
        adapter = ReleaseProgressAdapter(requireActivity(), arrayListOf())

        if (SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
            layoutNoLogin.visibility = View.GONE
            layoutNoRelease.visibility = View.VISIBLE
            releaseList.adapter = adapter

            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount()?.let { list ->
                    if (list?.size!! > 0) {
                        layoutNoRelease.visibility = View.GONE
                        releaseList.visibility = View.VISIBLE
                        adapter!!.addList(list as ArrayList<OrderDetailObject>,true)
                    }else{
                        layoutNoRelease.visibility = View.VISIBLE
                        releaseList.visibility = View.GONE
                    }
                }
            }
        } else {
            layoutNoLogin.visibility = View.VISIBLE
            layoutNoRelease.visibility = View.GONE
            releaseList.visibility = View.GONE
        }

        btLogin.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }


        btRelease.setOnClickListener {
            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount()?.let { list ->
                    Log.d("CardRelease--"," list = "+list)
                    if (list?.size!! > 0) {
                        layoutNoRelease.visibility = View.GONE
                        releaseList.visibility = View.VISIBLE
                        adapter!!.addList(list as ArrayList<OrderDetailObject>,true)
                    }else{
                        layoutNoRelease.visibility = View.VISIBLE
                        releaseList.visibility = View.GONE
                    }
                }
            }
        }


    }.root
}