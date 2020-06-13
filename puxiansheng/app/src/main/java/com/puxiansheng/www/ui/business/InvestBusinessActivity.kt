package com.puxiansheng.www.ui.business


import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.fragment_invest_business.*
import kotlinx.android.synthetic.main.fragment_invest_business.button_back
import kotlinx.android.synthetic.main.fragment_invest_business.button_search
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InvestBusinessActivity : MyBaseActivity() {
    private lateinit var viewModel: InvestBusnessViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_invest_business
    }

    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        button_search.addTextChangedListener {
            viewModel.title = it.toString()
        }
        button_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
                hideKeyboard(button_search)
                business_list.removeAllViews()
                viewModel.refresh()
                return@OnEditorActionListener true
            }
            false
        })

        viewModel = ViewModelProvider(this)[InvestBusnessViewModel::class.java]
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners)
            }

            DividerItemDecoration(this@InvestBusinessActivity, DividerItemDecoration.VERTICAL).let {
                it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
                business_list.addItemDecoration(it)
            }

            business_list.layoutManager = LinearLayoutManager(this@InvestBusinessActivity)
            BusinessAdapter(onItemSelect = { info ->
                val intent = Intent(
                    this@InvestBusinessActivity,
                    InvestBusinessDetailActivity::class.java
                )
                intent.putExtra("id", info?.id)
                intent.putExtra("url", info?.jump_param)
                startActivity(intent)
            },
                onItemCall = {
                    ConsultDialog(it?.id.toString()).show(
                        supportFragmentManager,
                        ConsultDialog::class.java.name
                    )
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

            if(intent.getStringExtra("title") != "*") {
                button_search.setText(intent.getStringExtra("title"))
                viewModel.title = intent.getStringExtra("title")
                business_list.removeAllViews()
                viewModel.refresh()
            }else {
                viewModel.refresh()
            }

        }
    }

    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}