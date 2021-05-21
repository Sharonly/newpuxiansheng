package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.databinding.FragmentCardReleaseBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.SimpleOrderViewModel
import kotlinx.android.synthetic.main.fragment_card_release.*
import kotlinx.coroutines.launch

class FastMyReleaseFragment : Fragment() {
    private lateinit var viewModel: SimpleOrderViewModel
    var adapter:ReleaseProgressAdapter?=null
    var mainActivity: MainActivity? = null

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
        mainActivity = MainActivity()


        val linearLayoutManager =
            LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        releaseList.layoutManager = linearLayoutManager

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(releaseList)

//        val width = (MyScreenUtil.getScreenWidth(requireContext()) - MyScreenUtil.dip2px(requireContext(), 54f))//计算滚动宽度=屏幕-左右各15-右边24
//        releaseList.scrollTo(width,0)
        adapter = ReleaseProgressAdapter(requireActivity(), arrayListOf())
        releaseList.adapter = adapter

        if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
            layoutNoLogin.visibility = View.GONE
            layoutNoRelease.visibility = View.VISIBLE
            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount()?.let { list ->
                    if (list?.size!! > 0) {
                        layoutNoRelease.visibility = View.GONE
                        releaseList.visibility = View.VISIBLE
                        adapter!!.addList(list as ArrayList<OrderDetailObject>, true)
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
            val intent = Intent(requireActivity(), InsertOrUpdateTransferOutOrderActivity::class.java)
            intent.putExtra("shopID", "0")
            startActivity(intent)
        }
    }.root


    override fun onResume() {
        super.onResume()
        if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
            layout_no_login.visibility = View.GONE
            layout_no_release.visibility = View.VISIBLE
        } else {
            layout_no_login.visibility = View.VISIBLE
            layout_no_release.visibility = View.GONE
        }
        if(layout_no_release.visibility == View.VISIBLE) {
            lifecycleScope.launch {
                viewModel.requestMyFastTransferCount()?.let { list ->
                    if (list?.size!! > 0) {
                        layout_no_release.visibility = View.GONE
                        release_list.visibility = View.VISIBLE
                        adapter!!.addList(list as ArrayList<OrderDetailObject>, true)
                    } else {
                        layout_no_release.visibility = View.VISIBLE
                        release_list.visibility = View.GONE
                    }
                }
            }
        }
    }


}