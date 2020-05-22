package com.puxiansheng.www.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentInvestBusinessDetailBinding

class InvestBusinessDetailFragment : AppFragment(){

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentInvestBusinessDetailBinding.inflate(inflater).apply {

    }.root
}