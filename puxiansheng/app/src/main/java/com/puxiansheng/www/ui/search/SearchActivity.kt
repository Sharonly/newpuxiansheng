package com.puxiansheng.www.ui.search

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.launch

class SearchActivity : MyBaseActivity() {

    private lateinit var searchViewModel: SearchViewModel
    var typeList= listOf("转铺","找店","百科","消息")

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun business() {

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        lifecycleScope.launch {
            searchViewModel.getHistorySearch()?.let { it ->
                it.forEach { menuItem ->
                    history_list.addView(Chip(history_list.context).apply {
                        text = menuItem.keyword
                    })
                }
            }

            searchViewModel.getRecommendSearch()?.let {
                it.forEach { menuItem ->
                    recommend_list.addView(Chip(history_list.context).apply {
                        text = menuItem.name
                    })
                }
            }
        }

        var arrayAdapter = ArrayAdapter(this, R.layout.item_select, typeList)
        type_spinner.adapter = arrayAdapter
        arrayAdapter.setDropDownViewResource(R.layout.item_pulldown_select)




//        type.onItemSelectedListener{}
    }

}