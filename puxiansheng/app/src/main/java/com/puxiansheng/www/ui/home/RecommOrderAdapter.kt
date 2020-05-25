package com.puxiansheng.www.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentHomeRecommendInfoItemBinding
import com.puxiansheng.www.databinding.FragmentOrderListTransferInItemBinding
import com.puxiansheng.www.databinding.FragmentOrderListTransferOutItemBinding
import com.puxiansheng.www.ui.info.InfoDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_home_recommend_info_item.*

class RecommOrderAdapter(
    var context: Context,
    private val onItemSelect: ((order: Order?) -> Unit)? = null,
    private var dataList: PagedList<Order>? = null
) : PagedListAdapter<Order, RecommOrderAdapter.OrderViewHolder>(Order.DIFF) {
    var type: Int = 0


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommOrderAdapter.OrderViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {

            when (viewType) {
                R.layout.fragment_order_list_transfer_out_item -> RecommendTransferOutViewHolder(it)
                R.layout.fragment_order_list_transfer_in_item -> RecommendTransferInViewHolder(it)
                R.layout.fragment_home_recommend_info_item -> RecommendInfoViewHolder(it)
                else -> EmptyOrderViewHolder(it)
            }
        }

    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun submitList(pagedList: PagedList<Order>?) {
        dataList = pagedList!!
        super.submitList(pagedList)
    }

    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: RecommOrderAdapter.OrderViewHolder, position: Int) {
        if (type != Order.Type.EMPTY.value() && !dataList.isNullOrEmpty()) {
            holder.bind(getItem(position))
            if (dataList?.get(position)?.shop?.data_type == "list_data") {
                Log.d("---type--", " ---list_data--- " + position)
                type = Order.Type.RECOMMEND_INFO.value()
            } else if (dataList?.get(position)?.shop?.data_type == "find_list") {
                type = Order.Type.TRANSFER_IN.value()
            } else if (dataList?.get(position)?.shop?.data_type == "transfer_list") {
                type = Order.Type.TRANSFER_OUT.value()
            }
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
        return when (type) {
            Order.Type.TRANSFER_OUT.value() -> R.layout.fragment_order_list_transfer_out_item
            Order.Type.TRANSFER_IN.value() -> R.layout.fragment_order_list_transfer_in_item
            Order.Type.RECOMMEND_INFO.value() -> R.layout.fragment_home_recommend_info_item
            else -> R.layout.fragment_order_list_empty
        }
    }


    inner class RecommendTransferOutViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentOrderListTransferOutItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.image.let { it ->
                it?.let { it1 -> binding.shopIcon.url(it1) }
            }

            item?.shop?.title.let { title ->
                binding.title.text = title
            }

//            item?.shop?.labels.let { it ->
//                binding.labeled.text = it
//            }

            item?.shop?.formattedFinalIndustry.let { it ->
                binding.industry.text = it
            }
            item?.shop?.formattedSize.let { it ->
                binding.size.text = it
            }

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

    inner class RecommendTransferInViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentOrderListTransferInItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.title.let { title ->
                binding.title.text = title
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

            item?.shop?.formattedFinalIndustry.let { it ->
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


    inner class RecommendInfoViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentHomeRecommendInfoItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            infolist.layoutManager =
                GridLayoutManager(context, 2)
            item?.shop?.articles.let { list ->
                infolist.adapter = list?.let {
                    ArticleTitleAdapter(it,context)
                }
            }

        }
    }

    inner class EmptyOrderViewHolder(
        override val containerView: View
    ) : RecommOrderAdapter.OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }
}