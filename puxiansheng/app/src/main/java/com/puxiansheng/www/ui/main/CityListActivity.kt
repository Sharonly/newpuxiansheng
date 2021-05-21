package com.puxiansheng.www.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.www.databinding.FragmentLocationNodeItemBinding
import com.puxiansheng.www.tools.SpUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_city_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class CityListActivity : MyBaseActivity() {

    private lateinit var locationViewModel: LocationViewModel
    private var hotAdapter: LocationNodesAdapter? = null
    private var listAdapter: LocationNodesAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_city_list
    }

    override fun business() {
        locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        initView()
    }


    fun initView() {
        cur_city.text = intent.getStringExtra("locationCity")?: SpUtils.get(API.USER_CITY_NAME,"东莞市").toString()
        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(
                resources.getDrawable(
                    R.drawable.recyclerview_divider_thin_full_width,
                    null
                )
            )
            city_list.addItemDecoration(it)
        }

        city_list.layoutManager = GridLayoutManager(this, 4)
        hot_list.layoutManager = GridLayoutManager(this, 4)

        button_back.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launch {
            locationViewModel.getRemoteSupportedCities()?.let {
//                hotAdapter = LocationNodesAdapter()
//                hot_list.adapter = hotAdapter
                city_list.adapter = it.nodes?.let { it1 -> LocationNodesAdapter(it1) }
                hot_list.adapter = it.hot?.let {it1 -> LocationNodesAdapter(it1)

                }
            }
        }
    }

    inner class LocationNodesAdapter(
        private val locationNodes: List<LocationNode>
    ) : RecyclerView.Adapter<LocationNodeViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = LocationNodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_location_node_item,
                parent,
                false
            )
        )

        override fun getItemCount(): Int = locationNodes.size

        override fun onBindViewHolder(
            holder: LocationNodeViewHolder,
            position: Int
        ) = holder.bind(locationNodes[position])
    }

    inner class LocationNodeViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = FragmentLocationNodeItemBinding.bind(containerView)

        fun bind(locationNode: LocationNode) {
            binding.cityName.text = locationNode.text
            binding.root.setOnClickListener {
                Log.d("currentCity","inType = "+intent.getIntExtra("inType",0))
                if(intent.getIntExtra("inType",0)==1){
                    LiveDataBus.get().with("currentCity")?.value = locationNode
                    SpUtils.put(API.USER_CITY_ID, locationNode.nodeID)
                    SpUtils.put(API.USER_CITY_NAME, locationNode.text)
                }else{
                    LiveDataBus.get().with("saveCity")?.value = locationNode
                }
                onBackPressed()
            }
        }
    }


}