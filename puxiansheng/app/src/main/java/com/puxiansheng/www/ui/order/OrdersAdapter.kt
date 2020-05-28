package com.puxiansheng.www.ui.order

import android.annotation.SuppressLint
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.*
import kotlinx.android.extensions.LayoutContainer


class OrdersAdapter(
    //定义加载数据显示的几种状态
    private val TYPE_LOAD_ALL_COMPLETE: Int = 1003,
    private val maxItem: Int? = null,
    var type: Int,
    private val onItemSelect: ((order: Order?) -> Unit)? = null,
    private val onEdit: ((order: Order?) -> Unit)? = null,
    private val onDelete: ((order: Order?) -> Unit)? = null
) : PagedListAdapter<Order, OrdersAdapter.OrderViewHolder>(Order.DIFF) {
    private var dataList: PagedList<Order>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
            when (viewType) {
                R.layout.fragment_order_list_transfer_out_item -> TransferOutOrderViewHolder(it)
                R.layout.fragment_order_list_transfer_in_item -> TransferInOrderViewHolder(it)

                R.layout.fragment_orders_mine_transfer_out_item -> MineTransferOutOrderViewHolder(it)
                R.layout.fragment_orders_mine_transfer_in_item -> MineTransferInOrderViewHolder(it)

                R.layout.fragment_recommend_order_list_transfer_out_item -> RecommendTransferOutViewHolder(
                    it
                )
                R.layout.fragment_recommend_order_list_transfer_in_item -> RecommendTransferInViewHolder(
                    it
                )
                else -> EmptyOrderViewHolder(it)
            }
        }



    override fun submitList(pagedList: PagedList<Order>?) {
        dataList = pagedList!!
        super.submitList(pagedList)
    }

    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }


    override fun getItemCount(): Int {
        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {

        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
            return R.layout.fragment_order_list_empty
        return when (type) {
            Order.Type.TRANSFER_OUT.value() -> R.layout.fragment_order_list_transfer_out_item
            Order.Type.TRANSFER_IN.value() -> R.layout.fragment_order_list_transfer_in_item

            Order.Type.TRANSFER_IN_PRIVATE.value() -> R.layout.fragment_orders_mine_transfer_in_item
            Order.Type.TRANSFER_OUT_PRIVATE.value() -> R.layout.fragment_orders_mine_transfer_out_item

            Order.Type.TRANSFER_OUT_HISTORY.value() -> R.layout.fragment_recommend_order_list_transfer_out_item
            Order.Type.TRANSFER_IN_HISTORY.value() -> R.layout.fragment_recommend_order_list_transfer_in_item

            else -> R.layout.fragment_order_list_empty
        }
    }

    override fun onBindViewHolder(
        holder: OrderViewHolder,
        position: Int
    ) {
        //if (type == Order.Type.EMPTY.value()) return

        /*maxItem?.let { max ->
            if (position > max - 1) return
        }*/

        /*getItem(position)?.let {
            holder.bind(it)
        }*/

        if (type != Order.Type.EMPTY.value())
            holder.bind(getItem(position))

    }


    abstract inner class OrderViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Order?)
    }

    inner class LoadingViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }


    inner class TransferOutOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        private val binding = FragmentOrderListTransferOutItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.images?.get(0)?.let { url ->
                binding.shopIcon.url(url)
            }

            item?.shop?.isHot?.let {
//                var str = ""
//                it.forEach { menuItem ->
//                    menuItem.text
//                    str += "<font color=\"menuItem.color\">menuItem.text</font>"
//                }
//                binding.labeled.text = (Html.fromHtml(str))
                if(it == 1) {
                    binding.isHot.visibility = View.VISIBLE
                }else{
                    binding.isHot.visibility = View.GONE
                }
            }

            item?.shop?.isRecommend?.let {
                if(it == 1) {
                    binding.isRecommend.visibility = View.VISIBLE
                }else{
                    binding.isRecommend.visibility = View.GONE
                }
            }

            item?.shop?.isLargeOrder?.let {
                if(it == 1) {
                    binding.isGood.visibility = View.VISIBLE
                }else{
                    binding.isGood.visibility = View.GONE
                }
            }


            item?.shop?.formattedDate?.let { date ->
                binding.date.text = date
            }

            item?.shop?.formattedRent?.let { rent ->
                binding.rent.text = rent
            }

            item?.shop?.formattedSize?.let { size ->
                item.shop?.formattedFee?.let { fee ->
                    binding.size.text = "$size"
                }
            }

            item?.shop?.formattedArea?.let { area ->
                binding.area.text = area
            }

            item?.shop?.formattedFinalIndustry?.let { industry ->
                if (industry.isNotEmpty()) binding.industry.visibility = View.VISIBLE
                binding.industry.text = industry
            }

            item?.shop?.isTop?.let {
                it
                if (it == 1) {
                    binding.icTop.visibility = View.VISIBLE
                }
            }

//            item?.shop?.formattedFinalLocationNode?.let { location ->
//                if (location.isNotEmpty()) binding.area.visibility = View.VISIBLE
//                binding.area.text = location
//            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }

    inner class TransferInOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        private val binding = FragmentOrderListTransferInItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            item?.shop?.title?.let {
                binding.title.text = it
            }

            item?.shop?.isHot?.let {
                if(it == 1) {
                    binding.isHot.visibility = View.VISIBLE
                }else{
                    binding.isHot.visibility = View.GONE
                }
            }

            item?.shop?.isRecommend?.let {
                if(it == 1) {
                    binding.isRecommend.visibility = View.VISIBLE
                }else{
                    binding.isRecommend.visibility = View.GONE
                }
            }

            item?.shop?.isLargeOrder?.let {
                if(it == 1) {
                    binding.isGood.visibility = View.VISIBLE
                }else{
                    binding.isGood.visibility = View.GONE
                }
            }


            item?.shop?.formattedFinalLocationNode?.let { area ->
                binding.area.text = area
            }

            item?.shop?.formattedIndustry?.let { industry ->
                binding.industry.text = industry
            }

            item?.shop?.formattedRent?.let { rent ->
                binding.rent.text = rent
            }

            item?.shop?.formattedSize?.let { size ->
                binding.size.text = size
            }

            item?.shop?.formattedDate?.let { date ->
                binding.date.text = date
            }

            item?.shop?.isTop?.let {
                it
                if (it == 1) {
                    binding.icTop.visibility = View.VISIBLE
                }
            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }


    inner class RecommendTransferOutViewHolder(override val containerView: View) :
        OrdersAdapter.OrderViewHolder(containerView) {
        private val binding = FragmentRecommendOrderListTransferOutItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {
            Log.d(
                "---item--",
                " ----RecommendTransferOutViewHolder--- formattedFinalIndustry = " + item?.shop?.formattedFinalIndustry + "  indusrt = " + item?.shop?.formattedIndustry
            )
            item?.shop?.image.let { it ->
                it?.let { it1 -> binding.shopIcon.url(it1) }
            }

            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.formattedIndustry.let { it ->
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
        OrdersAdapter.OrderViewHolder(containerView) {
        private val binding = FragmentRecommendOrderListTransferInItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        override fun bind(item: Order?) {

            item?.shop?.title.let { title ->
                binding.title.text = title
            }
            item?.shop?.formattedFinalLocationNode.let { it ->
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


    inner class MineTransferOutOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        private val binding = FragmentOrdersMineTransferOutItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n", "Range")
        override fun bind(item: Order?) {
            item?.shop?.title.let { title ->
                binding.title.text = title
            }

            item?.shop?.images?.get(0)?.let { url ->
                binding.shopIcon.url(url)
            }

            item?.shop?.formattedDate?.let { date ->
                binding.date.text = date
            }

            item?.shop?.formattedRent?.let { rent ->
                binding.rent.text = rent
            }

            item?.shop?.formattedSize?.let { size ->
                binding.size.text = size
            }

            item?.shop?.formattedFinalIndustry?.let { industry ->
                if (industry.isNotEmpty()) binding.industry.visibility = View.VISIBLE
                binding.industry.text = industry
            }

            item?.shop?.formattedArea?.let { area ->
                binding.area.text = area
            }

            binding.btEdit.setOnClickListener {
                onEdit?.let { onEdit -> onEdit(item) }
            }

            binding.btDelete.setOnClickListener {
                onDelete?.let { onDelete -> onDelete(item) }
            }

            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(item) }
            }
        }
    }

    inner class MineTransferInOrderViewHolder(
        override val containerView: View
    ) : OrderViewHolder(containerView) {
        private val binding = FragmentOrdersMineTransferInItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n", "Range")
        override fun bind(item: Order?) {
            item?.shop?.title?.let {
                binding.title.text = it
            }

            item?.shop?.formattedFinalIndustry?.let { industry ->
                binding.industry.text = industry
            }

            item?.shop?.formattedRent?.let { rent ->
                binding.rent.text = rent
            }

            item?.shop?.formattedSize?.let { size ->
                binding.size.text = size
            }

            item?.shop?.formattedDate?.let { date ->
                binding.date.text = date
            }

            item?.shop?.formattedArea?.let { area ->
                binding.area.text = area
            }

            binding.btDelete.setOnClickListener {
                onDelete?.let { onDelete -> onDelete(item) }
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
    ) : OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }
//
//    fun drawLableBg(){
//        val mutableBitmap: Bitmap = imageBitmap.copy(Bitmap.Config.ARGB_8888, true)
//        val canvas = Canvas(mutableBitmap)
//        var paint = Paint
//        paint.setStyle(Paint.Style.STROKE)//空心矩形框
//        paint.setColor(Color.RED);
//        canvas.drawRect(RectF(10F, 10F, 300F, 100F), paint)
//    }
}
