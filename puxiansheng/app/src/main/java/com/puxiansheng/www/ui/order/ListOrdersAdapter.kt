package com.puxiansheng.www.ui.order

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.common.urlBg
import kotlinx.android.extensions.LayoutContainer

class ListOrdersAdapter(var context: Context, var dataList: ArrayList<OrderDetailObject>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                .inflate(R.layout.fragment_order_list_transfer_out_item, parent, false)
            return TransferOutViewHolder(view)
        } else if (viewType == 2) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_order_list_transfer_in_item, parent, false)
            return TransferInViewHolder(view)
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


    override fun getItemViewType(position: Int): Int {
        if (!dataList.isNullOrEmpty()) {
            when (dataList?.get(position)?.data_type) {
                "transfer_list" -> return 1
                "find_list" -> return 2
            }
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TransferOutViewHolder) {
            var shopInfo = dataList[position]
            println("item---》${shopInfo}")
            shopInfo?.isLargeOrder.let { it ->
                if (it == 1) {
                    holder.normalLayout.visibility = View.GONE
                    holder.layoutBig.visibility = View.VISIBLE
                    shopInfo?.title.let { title ->
                        holder.bigTitle.text = title
                    }
                    shopInfo?.largeOrderImg.let {
                        it?.let { it1 -> holder.bgShop.urlBg(it1) }
                    }
                } else {
                    holder.normalLayout.visibility = View.VISIBLE
                    holder.layoutBig.visibility = View.GONE

                    shopInfo?.title.let { title ->
                        holder.shopTitle.text = title
                    }

                    shopInfo?.images?.get(0)?.let { url ->
                        holder.shopIcon.url(url)
                    }

                    shopInfo?.isSuccess?.let {
                        if (it == 1) {
                            holder.isRecommend.visibility = View.GONE
                            holder.isHot.visibility = View.GONE
                        } else {
                            shopInfo?.isHot?.let {
                                if (it == 1) {
                                    holder.isHot.visibility = View.VISIBLE
                                } else {
                                    holder.isHot.visibility = View.GONE
                                }
                            }

                            shopInfo?.isRecommend?.let {
                                if (it == 1) {
                                    holder.isRecommend.visibility = View.VISIBLE
                                } else {
                                    holder.isRecommend.visibility = View.GONE
                                }
                            }
                        }
                    }


                    shopInfo?.formattedSize?.let { date ->
                        holder.shopSize.text = date
                    }

                    shopInfo?.formattedRent?.let { rent ->
                        holder.shopRent.text = rent
                    }

                    shopInfo?.area_point_str?.let { area ->
                        holder.shopArea.text = area
                    }

                    shopInfo?.formattedFinalIndustry?.let { industry ->
                        if (industry.isNotEmpty()) holder.shopIndustry.visibility = View.VISIBLE
                        holder.shopIndustry.text = industry
                    }
                    shopInfo?.day_time?.let { date ->
                        holder.shopData.text = date
                    }

                }
                holder.root.setOnClickListener {
                    val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                    intent.putExtra("shopID", shopInfo?.shopID.toString())
                    context.startActivity(intent)
                }
            }


        } else if (holder is TransferInViewHolder) {
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


    abstract inner class OrderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: Order?)
    }




    inner class TransferOutViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.item_layout)
        val normalLayout = containerView.findViewById<View>(R.id.layout_normal)
        val shopIcon = containerView.findViewById<ImageView>(R.id.shop_icon)
        val shopTitle = containerView.findViewById<TextView>(R.id.title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry)
        val shopSize = containerView.findViewById<TextView>(R.id.size)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val shopData = containerView.findViewById<TextView>(R.id.date)
        val isRecommend = containerView.findViewById<TextView>(R.id.is_recommend)
        val isHot = containerView.findViewById<TextView>(R.id.is_hot)
        val layoutBig = containerView.findViewById<CardView>(R.id.layout_big)
        val bgShop = containerView.findViewById<ImageView>(R.id.bg_shop)
        val bigTitle = containerView.findViewById<TextView>(R.id.big_title)
    }



    //右边viewholder
    inner class TransferInViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.item_layout)
        val shopTitle = containerView.findViewById<TextView>(R.id.title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry)
        val shopSize = containerView.findViewById<TextView>(R.id.size)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val shopData = containerView.findViewById<TextView>(R.id.date)
    }



    //空viewholder
    inner class EmptyOrderViewHolder(override val containerView: View) :
        ListOrdersAdapter.OrderViewHolder(containerView) {

        override fun bind(item: Order?) {

        }
    }


}