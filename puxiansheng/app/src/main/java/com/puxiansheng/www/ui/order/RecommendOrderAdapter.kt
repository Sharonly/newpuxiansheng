package com.puxiansheng.www.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.RecommendOrderShop
import com.puxiansheng.www.R
import com.puxiansheng.www.common.drawableTop
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.DialogSelectiveMenuItemBinding
import com.puxiansheng.www.databinding.RecommendOrderItemBinding
import kotlinx.android.extensions.LayoutContainer

class RecommendOrderAdapter(
    private var list: List<RecommendOrderShop>,  private val onItemSelect: ((order: RecommendOrderShop?) -> Unit)? = null
    ) : RecyclerView.Adapter<RecommendOrderAdapter.OrderItemViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecommendOrderAdapter.OrderItemViewHolder = OrderItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recommend_order_item,
                parent,
                false
            )
        )

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: RecommendOrderAdapter.OrderItemViewHolder,
            position: Int
        ) {
            holder.bind(list[position])
        }

    fun setMenuData(listData: List<RecommendOrderShop>) {
        list = listData.toMutableList()
        notifyDataSetChanged()
    }

    inner class OrderItemViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val binding: RecommendOrderItemBinding =
            RecommendOrderItemBinding.bind(containerView)

        fun bind(menuItem: RecommendOrderShop) {
            binding.shopTitle.text = menuItem.title
            binding.shopLabel.text =menuItem.area_category
            binding.shopImg.url(menuItem.icon)
            binding.rent.text = menuItem.view_rent
            binding.viewNum.text = menuItem.view_count_str
            binding.size.text = menuItem.view_acreage

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(menuItem) }
            }
        }
    }
}