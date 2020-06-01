package com.puxiansheng.www.ui.mine.relase.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.*
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.selects.select


class ReleaseStateOrdersAdapter(
    var context: Context,
    private val onItemDelete: ((order: Order?) -> Unit)? = null,
    private val onItemFresh: ((order: Order?) -> Unit)? = null,
    private var dataList: List<Order>, var type: Int
) : RecyclerView.Adapter<ReleaseStateOrdersAdapter.OrderViewHolder>() {
    var orderType = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
                R.layout.fragment_orders_public_item -> PublicTransferInViewHolder(it)
                R.layout.fragment_order_list_empty -> EmptyOrderViewHolder(it)
                else -> EmptyOrderViewHolder(it)
            }

        }

    fun setMenuData(listData: List<Order>) {
        dataList = listData.toMutableList()
        notifyDataSetChanged()
    }

    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + dataList.size
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        if (getDataCount() == 0) {
            type = Order.Type.EMPTY.value()
            return R.layout.fragment_order_list_empty
        }
        return when (type) {
            Order.Type.USER_PUBLIC_ORDER.value() -> R.layout.fragment_orders_public_item
            else -> R.layout.fragment_order_list_empty
        }
    }


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


    inner class PublicTransferInViewHolder(override val containerView: View) :
        OrderViewHolder(containerView) {
        private val binding = FragmentOrdersPublicItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n", "Range")
        override fun bind(item: Order?) {
            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.isVip.let {
                if (it == 1) {
                    binding.isVip.visibility = View.VISIBLE
                    binding.btDelete.visibility = View.GONE
                    binding.btEdit.visibility = View.GONE
                    binding.btRefresh.visibility = View.GONE
                } else {
                    binding.isVip.visibility = View.INVISIBLE
                    binding.btDelete.visibility = View.VISIBLE
                    binding.btEdit.visibility = View.VISIBLE
                    binding.btRefresh.visibility = View.VISIBLE
                }
            }

            item?.status?.let { status ->
                binding.status.text = status.text
                binding.status.setTextColor(Color.parseColor(status.color))
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

            item?.shop?.data_type.let {
                if (it == "transfer_shop") {
                    binding.icType.setImageResource(R.mipmap.ic_transfer_out)
                    orderType = 0
                } else if (it == "find_shop") {
                    binding.icType.setImageResource(R.mipmap.ic_transfer_in)
                    orderType = 1
                }
            }

            binding.btEdit.setOnClickListener {
                if (orderType == 0) {
                    val intent = Intent(context, InsertOrUpdateTransferOutOrderActivity::class.java)
                    intent.putExtra("shopID", item?.shop?.jump_param)
                    context.startActivity(intent)
                } else if (orderType == 1) {
                    val intent = Intent(context, InsertOrUpdateTransferInOrderActivity::class.java)
                    intent.putExtra("shopID", item?.shop?.jump_param)
                    context.startActivity(intent)
                }
            }

            binding.btRefresh.setOnClickListener {
                onItemFresh?.let { select -> select(item) }
            }

            binding.btDelete.setOnClickListener {
                onItemDelete?.let { delete ->
                    delete(item)
                }
            }

            binding.root.setOnClickListener {
                if (orderType == 0) {
                    val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                    intent.putExtra("shopID", item?.shop?.jump_param)
                    context.startActivity(intent)
                } else if (orderType == 1) {
                    val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                    intent.putExtra("shopID", item?.shop?.jump_param)
                    context.startActivity(intent)
                }
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
