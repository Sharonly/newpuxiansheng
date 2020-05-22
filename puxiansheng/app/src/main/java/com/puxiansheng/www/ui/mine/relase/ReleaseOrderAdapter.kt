package com.puxiansheng.www.ui.mine.relase

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentMyRelaseOrderItemBinding
import kotlinx.android.extensions.LayoutContainer

class ReleaseOrderAdapter(
    var type: Int, private val onItemSelect: ((order: Order?) -> Unit)? = null,
    private val onEdit: ((order: Order?) -> Unit)? = null,
    private val onRefresh: ((order: Order?) -> Unit)? = null,
    private val onDelete: ((order: Order?) -> Unit)? = null
) : PagedListAdapter<Order, ReleaseOrderAdapter.OrderViewHolder>(Order.DIFF) {
    private var dataList: PagedList<Order>? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReleaseOrderAdapter.OrderViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
//                R.layout.fragment_order_list_transfer_out_item -> MineReleaseTransferOrderViewHolder(it)
//                R.layout.fragment_orders_mine_transfer_out_item -> MineReleaseTransferOrderViewHolder(it)
//                R.layout.fragment_order_list_transfer_in_item -> MineReleaseTransferOrderViewHolder(it)
                R.layout.fragment_my_relase_order_item -> MineReleaseTransferOrderViewHolder(it)
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
//        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {

        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty

        return when (type) {
            Order.Type.TRANSFER_OUT.value() -> R.layout.fragment_order_list_transfer_out_item
            Order.Type.TRANSFER_OUT_PRIVATE.value() -> R.layout.fragment_orders_mine_transfer_out_item
            Order.Type.TRANSFER_IN.value() -> R.layout.fragment_order_list_transfer_in_item
            Order.Type.TRANSFER_IN_PRIVATE.value() -> R.layout.fragment_orders_mine_transfer_in_item

            Order.Type.TRANSFER_OUT_HISTORY.value() -> R.layout.fragment_order_list_transfer_out_item
            Order.Type.TRANSFER_IN_HISTORY.value() -> R.layout.fragment_order_list_transfer_in_item

            Order.Type.TRANSFER_OUT_FAVORITE.value() -> R.layout.fragment_order_list_transfer_out_item
            Order.Type.TRANSFER_IN_FAVORITE.value() -> R.layout.fragment_order_list_transfer_in_item
            else -> R.layout.fragment_order_list_empty
        }
    }


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        if (type != Order.Type.EMPTY.value())
            holder.bind(getItem(position))
    }


    inner class MineReleaseTransferOrderViewHolder(override val containerView: View) : ReleaseOrderAdapter.OrderViewHolder(containerView) {
        private val binding = FragmentMyRelaseOrderItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n", "Range")
        override fun bind(item: Order?) {
            item?.shop?.title?.let {
                binding.orderTitle.text = it
            }

//            item?.shop?.formattedFinalLocationNode?.let { area ->
//                item.shop?.formattedFinalIndustry?.let { industry ->
//                    binding.areaAndIndustry.text =
//                        "求租：$area ${if (area != "") "-" else ""} $industry"
//                }
//            }

            item?.shop?.formattedRent?.let { rent ->
                binding.rent.text = rent
            }

            item?.shop?.formattedSize?.let { size ->
                binding.size.text = size
            }

//            item?.shop?.formattedDate?.let { date ->
//                binding.date.text = date
//            }

//            item?.state?.let { state ->
//                binding.state.text = state.text
//                binding.state.setTextColor(Color.parseColor(state.color))
//            }


            binding.btDelete.setOnClickListener {
                onDelete?.let { onDelete -> onDelete(item) }

            }

            binding.btRefresh.setOnClickListener {
                onRefresh?.let { onRefresh -> onRefresh(item) }
            }
            binding.btEdit.setOnClickListener {
                onEdit?.let { onEdit -> onEdit(item) }
            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }

    inner class EmptyOrderViewHolder(
        override val containerView: View
    ) : ReleaseOrderAdapter.OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }

    abstract inner class OrderViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Order?)
    }


}