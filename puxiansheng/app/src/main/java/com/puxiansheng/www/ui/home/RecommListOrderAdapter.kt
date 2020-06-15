package com.puxiansheng.www.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.ArticleBean
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.*
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_home_recommend_info_item.*

class RecommListOrderAdapter(var context: Context, var dataList: ArrayList<OrderDetailObject>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var type: Int = Order.Type.EMPTY.value()


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommListOrderAdapter.OrderViewHolder =
//        LayoutInflater.from(parent.context).inflate(viewType, parent, false).let {
//            when (viewType) {
//                R.layout.fragment_recommend_order_list_transfer_out_item -> RecommendTransferOutViewHolder(it)
//                R.layout.fragment_recommend_order_list_transfer_in_item -> RecommendTransferInViewHolder(it)
//                R.layout.fragment_home_recommend_info_item -> RecommendInfoViewHolder(it)
//                else -> EmptyOrderViewHolder(it)
//            }
//
//        }
//

    fun addList(tempList: ArrayList<OrderDetailObject>, isClean: Boolean) {
        if (isClean) {
            dataList.clear()
        }
        dataList.addAll(tempList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_recommend_order_list_transfer_out_item, parent, false)
            return RecommendTransferOutViewHolder(view)
        } else if (viewType == 2) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_recommend_order_list_transfer_in_item, parent, false)
            return RecommendTransferInViewHolder(view)
        } else if (viewType == 3) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_home_recommend_info_item, parent, false)
            return RecommendInfoViewHolder(view)
        }
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_order_list_empty, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }


    fun getDataCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 1 else dataList.size
    }


//    override fun submitList(pagedList: PagedList<Order>?) {
//        dataList = pagedList!!
//        super.submitList(pagedList)
//    }

//    override fun getItemCount(): Int {
//        if (type == Order.Type.EMPTY.value()) return 1 + super.getItemCount()
//        return super.getItemCount()
//    }


    override fun getItemViewType(position: Int): Int {
        if (!dataList.isNullOrEmpty()) {
//            when (dataList?.get(position)?.shop?.data_type) {
            when (dataList?.get(position)?.data_type) {
                "transfer_list" -> return 1
                "find_list" -> return 2
                "list_data" -> return 3
            }
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecommendTransferOutViewHolder) {
            var shopInfo = dataList[position]
            println("item---》${shopInfo}")
            holder.shopIcon.url(shopInfo.image)
            holder.shopTitle.text = shopInfo.title
            holder.shopIndustry.text = shopInfo.formattedFinalIndustry
            holder.shopSize.text = shopInfo.formattedSize
            holder.shopRent.text = shopInfo.formattedRent
            holder.shopArea.text = shopInfo.area_point_str
            holder.shopData.text = shopInfo.day_time
            holder.root.setOnClickListener {
                val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", shopInfo?.jump_param)
                context.startActivity(intent)
            }
        } else if (holder is RecommendInfoViewHolder) {
            var shopInfo = dataList[position]
            holder.infoList.layoutManager = GridLayoutManager(context, 2)
            holder.infoList.adapter = shopInfo?.articles.let {
                ArticleTitleAdapter(it as List<ArticleBean>, context)
            }

        } else if (holder is RecommendTransferInViewHolder) {
            var shopInfo = dataList[position]
            holder.shopTitle.text = shopInfo.title
            holder.shopIndustry.text = shopInfo.formattedFinalIndustry
            holder.shopSize.text = shopInfo.view_acreage_un_prefix
            holder.shopRent.text = shopInfo.view_rent_un_prefix
            holder.shopArea.text = shopInfo.formattedFinalLocationNode
            holder.shopData.text = shopInfo.day_time
            holder.root.setOnClickListener {
                val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", shopInfo?.jump_param)
                context.startActivity(intent)
            }
        }
    }

//    override fun onBindViewHolder(holder: RecommListOrderAdapter.OrderViewHolder, position: Int) {
//        if (type != Order.Type.EMPTY.value() && !dataList.isNullOrEmpty()) {
//             //  holder.bind(getItem(position))
//
//        }
//
//    }

    abstract inner class OrderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Order?)
    }

//    override fun getItemViewType(position: Int): Int {
//        println("viewtype---》${position}--->${dataList?.size}")
//        if (type == Order.Type.EMPTY.value() && position == itemCount - 1)
//            return R.layout.fragment_order_list_empty
//        if(!dataList.isNullOrEmpty()) {
//            return when (dataList?.get(position)?.shop?.data_type) {
//                "transfer_list" -> R.layout.fragment_recommend_order_list_transfer_out_item
//                "find_list" -> R.layout.fragment_recommend_order_list_transfer_in_item
//                "list_data" -> R.layout.fragment_home_recommend_info_item
//                else -> R.layout.fragment_order_list_empty
//            }
//        }
//        return R.layout.fragment_order_list_empty
//    }


    inner class RecommendTransferOutViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.item_layout)
        val shopIcon = containerView.findViewById<ImageView>(R.id.shop_icon)
        val shopTitle = containerView.findViewById<TextView>(R.id.title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry)
        val shopSize = containerView.findViewById<TextView>(R.id.size)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val shopData = containerView.findViewById<TextView>(R.id.date)

    }

    //左边viewholder
//    inner class RecommendTransferOutViewHolder(override val containerView: View) :
//        OrderViewHolder(containerView) {
//        private val binding = FragmentRecommendOrderListTransferOutItemBinding.bind(containerView)
//
//        @SuppressLint("SetTextI18n")
//        override fun bind(item: Order?) {
//            item?.shop?.image.let { it ->
//                it?.let { it1 -> binding.shopIcon.url(it1) }
//            }
//
//            item?.shop?.title.let { title ->
//                binding.title.text = title
//            }
//
//            item?.shop?.formattedFinalIndustry.let { it ->
//                binding.industry.text = it
//            }
//
//            item?.shop?.formattedSize.let { it ->
//                binding.size.text = it
//            }
//
//            item?.shop?.formattedRent.let { it ->
//                binding.rent.text = it
//            }
//
//            item?.shop?.formattedArea.let { it ->
//                binding.area.text = it
//            }
//
//
//            item?.shop?.formattedDate.let { it ->
//                binding.date.text = it
//            }
//
//
////            binding.root.setOnClickListener {
////                onItemSelect?.let { select -> select(item) }
////            }
//        }
//    }


    //左边消息viewholder
    inner class RecommendInfoViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val infoList = containerView.findViewById<RecyclerView>(R.id.infolist)

    }


    //左边消息viewholder
//    inner class RecommendInfoViewHolder(override val containerView: View) :
//        OrderViewHolder(containerView) {
//        private val binding = FragmentHomeRecommendInfoItemBinding.bind(containerView)
//
//        @SuppressLint("SetTextI18n")
//        override fun bind(item: Order?) {
//            infolist.layoutManager = GridLayoutManager(context, 2)
//            item?.shop?.articles.let { list ->
//                infolist.adapter = list?.let {
//                    ArticleTitleAdapter(it, context)
//                }
//            }
//
//        }
//    }


    //右边viewholder
    inner class RecommendTransferInViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.layout_item)
        val shopTitle = containerView.findViewById<TextView>(R.id.shop_title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry)
        val shopSize = containerView.findViewById<TextView>(R.id.size)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val shopData = containerView.findViewById<TextView>(R.id.date)
    }

//    inner class RecommendTransferInViewHolder(override val containerView: View) :
//        OrderViewHolder(containerView) {
//        private val binding = FragmentRecommendOrderListTransferInItemBinding.bind(containerView)
//
//        @SuppressLint("SetTextI18n")
//        override fun bind(item: Order?) {
//            item?.shop?.title.let { title ->
//                binding.title.text = title
//            }
//            item?.shop?.formattedArea.let { it ->
//                binding.area.text = it
//            }
//
//            item?.shop?.formattedRent.let { it ->
//                binding.rent.text = it
//            }
//
//            item?.shop?.formattedSize.let { it ->
//                binding.size.text = it
//            }
//
//            item?.shop?.formattedFinalIndustry.let { it ->
//                binding.industry.text = it
//            }
//
//            item?.shop?.formattedDate.let { it ->
//                binding.date.text = it
//            }
//
////            binding.root.setOnClickListener {
////                onItemSelect?.let { select -> select(item) }
////            }
//        }
//    }


    //空viewholder
    inner class EmptyOrderViewHolder(override val containerView: View) :
        RecommListOrderAdapter.OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }


}