package com.puxiansheng.www.ui.home


import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.fragment_invest_business.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InvestBusinessActivity : MyBaseActivity(){
    private lateinit var viewModel: InvestBusnessViewModel

    override fun getLayoutId(): Int {
       return R.layout.fragment_invest_business
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[InvestBusnessViewModel::class.java]
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners)
            }

            viewModel.requestBusinessList()?.let {

            }

            DividerItemDecoration(this@InvestBusinessActivity, DividerItemDecoration.VERTICAL).let {
                it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
                business_list.addItemDecoration(it)
            }

            business_list.layoutManager = LinearLayoutManager(this@InvestBusinessActivity)
            BusinessAdapter(onItemSelect = { info ->
                val intent = Intent(this@InvestBusinessActivity, InvestBusinessDetailActivity::class.java)
                intent.putExtra("id", info?.id)
                startActivity(intent) },
                onItemCall = {

                }).let { adapter ->
                business_list.adapter = adapter
                lifecycleScope.launch {
                    LivePagedListBuilder<Int, BusinessBean>(
                        viewModel.getBusinesssFromLocal(),
                        5
                    ).apply {
                        setBoundaryCallback(object : PagedList.BoundaryCallback<BusinessBean>() {
                            override fun onItemAtEndLoaded(itemAtEnd: BusinessBean) {
                                super.onItemAtEndLoaded(itemAtEnd)
                                viewModel.loadMore(
                                )
                            }
                        })
                    }.build().observe(this@InvestBusinessActivity, Observer {
                        adapter.submitList(it)
                    })
                }
            }

            viewModel.refresh()

        }
    }

}