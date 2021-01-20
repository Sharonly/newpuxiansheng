package com.puxiansheng.www.ui.release.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.http.AreaObject
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.AreaItemBinding
import kotlinx.android.extensions.LayoutContainer

class MultiAreaAdapter(
    var lists: ArrayList<LocationNode>
) : RecyclerView.Adapter<MultiAreaAdapter.FacilityItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacilityItemViewHolder = FacilityItemViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.area_item,
            parent,
            false
        )
    )


    fun addList(tempList: ArrayList<LocationNode>, isClean: Boolean) {
        if (isClean) {
            lists.clear()
        }
        if (tempList != null) {
            lists.addAll(tempList)
        }
        notifyDataSetChanged()
    }

    fun add(item: LocationNode) {
        lists.add(item)
        notifyDataSetChanged()
    }


    fun delete(item: LocationNode) {
        lists.remove(item)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(
        holder: FacilityItemViewHolder,
        position: Int
    ) {
        holder.bind(lists[position])
    }

  inner  class FacilityItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: AreaItemBinding =
            AreaItemBinding.bind(containerView)

        fun bind(menuItem: LocationNode) {
            binding.txtName.text = menuItem.text
            binding.btClose.setOnClickListener {
                delete(menuItem)
                listener?.onDelete(menuItem)
            }
        }
    }

    var listener: OnDeleteListener? = null

    interface OnDeleteListener {
        fun onDelete(menuItem: LocationNode)
    }

}