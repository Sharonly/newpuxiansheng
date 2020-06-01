package com.puxiansheng.www.ui.mine.relase.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.*
import kotlinx.android.extensions.LayoutContainer


class PublicOrdersAdapter(
    private val onItemSelect: ((order: Order?) -> Unit)? = null,
    private var dataList: List<Order>
) :  RecyclerView.Adapter<PublicOrdersAdapter.OrderViewHolder>() {
    var type: Int = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
                R.layout.fragment_orders_public_transfer_out_item -> PublicTransferOutViewHolder(it)
                R.layout.fragment_orders_public_transfer_in_item -> PublicTransferInViewHolder(it)
                else -> EmptyOrderViewHolder(it)
            }

        }

    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + dataList.size
        return dataList.size
    }

    fun setMenuData(listData: List<Order>) {
        dataList = listData.toMutableList()
        notifyDataSetChanged()
    }


//    override fun getItemCount(): Int {
//        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
//        return super.getItemCount()
//    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        if (type != Order.Type.EMPTY.value()) {
            holder.bind(dataList!![position])
        }
    }

    abstract inner class OrderViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Order?)
    }

    override fun getItemViewType(position: Int): Int {
        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        if (!dataList.isNullOrEmpty()) {
            return when (dataList?.get(position)?.shop?.data_type) {
                "transfer_shop" -> R.layout.fragment_orders_public_transfer_out_item
                "find_shop" -> R.layout.fragment_orders_public_transfer_in_item
                else -> R.layout.fragment_order_list_empty
            }
        }
        return R.layout.fragment_order_list_empty
    }


    inner class PublicTransferOutViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentOrdersPublicTransferOutItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.image.let { it ->
                it?.let { it1 -> binding.shopIcon.url(it1) }
            }

            item?.shop?.isVip.let {
                if(it == 1){
                    binding.isVip.visibility = View.VISIBLE
                }else{
                    binding.isVip.visibility = View.INVISIBLE
                }
            }

            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.category_acreage.let { it ->
                binding.industryAndSize.text = it
            }

//            item?.shop?.formattedSize.let { it ->
//                binding.size.text = it
//            }

            item?.shop?.formattedRent.let { it ->
                binding.rent.text = it
            }

            item?.shop?.formattedArea.let { it ->
                binding.area.text = it
            }


            item?.shop?.formattedDate.let { it ->
                binding.date.text = it
            }


            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }

    inner class PublicTransferInViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentOrdersPublicTransferInItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.isVip.let {
                if(it == 1){
                    binding.isVip.visibility = View.VISIBLE
                }else{
                    binding.isVip.visibility = View.INVISIBLE
                }
            }

            item?.shop?.formattedArea.let { it ->
                binding.area.text = it
            }

            item?.shop?.formattedRent.let { it ->
                binding.rent.text = it
            }

            item?.shop?.formattedSize.let { it ->
                binding.size.text = it
            }

            item?.shop?.formattedIndustry.let { it ->
                binding.industry.text = it
            }

            item?.shop?.formattedDate.let { it ->
                binding.date.text = it
            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }


    inner class EmptyOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        override fun bind(item: Order?) {
        }
    }
}
