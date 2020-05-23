package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentInfoHomeBinding
import com.puxiansheng.www.databinding.FragmentOrderListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_info_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

class InfoHomeListFragment : AppFragment() {

    private lateinit var infoListViewModel: InfoListViewModel
    private lateinit var appModel: MainViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        infoListViewModel = ViewModelProvider(this)[InfoListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentInfoHomeBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

//        appModel.currentSignatureToken.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                //isLoaded = true
                infoListViewModel.getInfoCategoriesFromRemote()?.let {
                    //                    println(it)
                    pager.adapter = InfoPagerAdapter(
                        fragmentManager = childFragmentManager,
                        fragments = it.map { category ->
                            InfoListFragment.newInstance(category = category.menuID.toInt())
                        },
                        titles = it.map { text ->
                            text.text
                        })
                    pager.offscreenPageLimit = 5
                    tabs.setupWithViewPager(pager)
                }
            }
//        })

    }.root

}