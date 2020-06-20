package com.puxiansheng.www.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.databinding.FragmentLocationNodeItemBinding
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class LocationActivity : MyBaseActivity() {

    private lateinit var locationViewModel: LocationViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_location
    }

    override fun business() {
        locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        initView()
    }


    fun initView() {
        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(
                resources.getDrawable(
                    R.drawable.recyclerview_divider_thin_full_width,
                    null
                )
            )
            city_list.addItemDecoration(it)
        }

        city_list.layoutManager = LinearLayoutManager(this)

        button_back.setOnClickListener {
            onBackPressed()
        }

        lifecycleScope.launch {
            locationViewModel.getRemoteSupportedCities()?.let {
                city_list.adapter = LocationNodesAdapter(it)
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
                LiveDataBus.get().with("currentCity")?.value = locationNode
                Log.d("---city--"," --- put city = "+locationNode.nodeID.toString()+"  name = "+locationNode.text)
                SharedPreferencesUtil.put(API.USER_CITY_ID, locationNode.nodeID)
                SharedPreferencesUtil.put(API.USER_CITY_NAME, locationNode.text)
                onBackPressed()
            }
        }
    }


}