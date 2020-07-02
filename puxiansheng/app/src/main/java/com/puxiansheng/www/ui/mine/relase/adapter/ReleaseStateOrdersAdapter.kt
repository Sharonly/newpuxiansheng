package com.puxiansheng.www.ui.mine.relase.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.*
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import kotlinx.android.extensions.LayoutContainer


class ReleaseStateOrdersAdapter(
    var context: Context,
    private val onItemDelete: ((order: Order?) -> Unit)? = null,
    private val onItemFresh: ((order: Order?) -> Unit)? = null,
    var list:List<Order>? = null,
    var type: Int? = null
) : RecyclerView.Adapter<ReleaseStateOrdersAdapter.OrderViewHolder>() {
    var orderType = Order.Type.EMPTY.value()
    private var dataList: List<Order> = listOf()

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

    fun setType(type: Int){
        orderType = type
    }

    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemCount(): Int {
        if (orderType == Order.Type.EMPTY.value()) return 1 + dataList.size
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (orderType == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        if (getDataCount() == 0) {
            orderType = Order.Type.EMPTY.value()
            return R.layout.fragment_order_list_empty
        }
        return when (orderType) {
            Order.Type.USER_PUBLIC_ORDER.value() -> R.layout.fragment_orders_public_item
            else -> R.layout.fragment_order_list_empty
        }
    }


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        if (orderType != Order.Type.EMPTY.value()) {
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

                item?.state?.let { status ->
                    binding.status.text = status.text
                    binding.status.setTextColor(Color.parseColor(status.color))

                    if(status.text == "已发布" || status.text == "已过期"||status.text == "在审核"){
                        binding.btRefresh.visibility =  View.VISIBLE
                    }else{
                        binding.btRefresh.visibility =  View.GONE
                    }

                    if(status.text == "已下架" || status.text == "已完结" || status.text == "已成交"){
                        binding.btEdit.visibility =  View.GONE
                    }else{
                        binding.btEdit.visibility =  View.VISIBLE
                    }

                    //TODO 不确定是这样判断
//                if(status.text == "再审核" || status.text == "未通过" || status.text == "未审核"){
//                    binding.btRefresh.visibility =  View.INVISIBLE
//                }else{
//                    binding.btRefresh.visibility =  View.VISIBLE
//                }

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

            item?.shop?.data_type.let { shop ->
                if (shop == "transfer_shop") {
                    binding.icType.setImageResource(R.mipmap.ic_transfer_out)
                } else if (shop == "find_shop") {
                    binding.icType.setImageResource(R.mipmap.ic_transfer_in)
                }

                binding.btEdit.setOnClickListener {
                    if (shop == "transfer_shop") {
                        val intent = Intent(context, InsertOrUpdateTransferOutOrderActivity::class.java)
                        intent.putExtra("shopID", item?.shop?.shopID?.toString())
                        context.startActivity(intent)
                    } else if (shop == "find_shop") {
                        val intent = Intent(context, InsertOrUpdateTransferInOrderActivity::class.java)
                        intent.putExtra("shopID", item?.shop?.shopID?.toString())
                        context.startActivity(intent)
                    }
                }

                binding.root.setOnClickListener {
                    if (shop == "transfer_shop") {
                        val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                        intent.putExtra("shopID", item?.shop?.shopID.toString())
                        context.startActivity(intent)
                    } else if (shop == "find_shop") {
                        val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                        intent.putExtra("shopID", item?.shop?.shopID.toString())
                        context.startActivity(intent)
                    }
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


        }
    }

    interface onDeleteListener{
        fun delete(order: OrderDetailObject)
    }

    inner class EmptyOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        override fun bind(item: Order?) {
        }
    }
}
