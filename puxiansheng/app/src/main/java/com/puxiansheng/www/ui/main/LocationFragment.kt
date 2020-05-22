package com.puxiansheng.www.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentLocationBinding
import com.puxiansheng.www.databinding.FragmentLocationNodeItemBinding
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class  LocationFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private lateinit var appViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        appViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentLocationBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_thin_full_width, null))
            cityList.addItemDecoration(it)
        }

        cityList.layoutManager = LinearLayoutManager(requireContext())

        buttonBack.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
        }

        lifecycleScope.launch {
            locationViewModel.getRemoteSupportedCities()?.let {
                cityList.adapter = LocationNodesAdapter(it)
            }
        }
    }.root

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
                appViewModel.currentCity.postValue(locationNode)
                SharedPreferencesUtil.put(API.USER_CITY_ID, locationNode.nodeID)
                SharedPreferencesUtil.put(API.USER_CITY_NAME, locationNode.text)
                Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigateUp()
            }
        }
    }
}