package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.common.urlBg
import com.puxiansheng.www.ui.mine.relase.MyReleaseAllActivity
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.extensions.LayoutContainer

class ReleaseProgressAdapter(var context: Context, var dataList: ArrayList<OrderDetailObject>) :
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
                .inflate(R.layout.fragment_fast_progress_out_item, parent, false)
            return TransferOutViewHolder(view)
        } else if (viewType == 2) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_fast_progress_in_item, parent, false)
            return TransferInViewHolder(view)
        }
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_order_list_empty, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }


    fun getDataCount(): Int {
        return dataList?.size
    }

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 1 else dataList.size
    }


    override fun getItemViewType(position: Int): Int {
        if (!dataList.isNullOrEmpty()) {
            var type = dataList?.get(position)?.data_type
            if (type.contains("transfer_shop")) {
                return 1
            } else {
                return 2
            }
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TransferOutViewHolder) {
            var shopInfo = dataList[position]
            shopInfo?.title.let { title ->
                holder.shopTitle.text = title
            }

            shopInfo?.images?.get(0)?.let { url ->
                holder.shopIcon.url(url)
            }

            shopInfo?.formattedRent?.let { rent ->
                holder.shopRent.text = rent
            }

            shopInfo?.area_point_str?.let { area ->
                holder.shopArea.text = area
            }

            holder.btMore.setOnClickListener {
                val intent = Intent(context, MyReleaseAllActivity::class.java)
                context.startActivity(intent)
            }
            holder.status.text = shopInfo.state?.text

            holder.index.text = (position+1).toString()
            holder.count.text = "/" + dataList?.size

            holder.root.setOnClickListener {
                val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", shopInfo?.jump_param)
                context.startActivity(intent)
            }

        } else if (holder is TransferInViewHolder) {
            var shopInfo = dataList[position]
            holder.shopTitle.text = shopInfo.title
            holder.shopRent.text = shopInfo.view_rent_un_prefix
            holder.status.text = shopInfo.state?.text
            holder.shopArea.text =
                shopInfo.show_area
            holder.btMore.setOnClickListener {
                val intent = Intent(context, MyReleaseAllActivity::class.java)
                context.startActivity(intent)
            }

            holder.index.text = (position+1).toString()
            holder.count.text = "/" + dataList?.size

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


    class TransferOutViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<LinearLayout>(R.id.layout_out)
        val shopIcon = containerView.findViewById<ImageView>(R.id.shop_icon)
        val shopTitle = containerView.findViewById<TextView>(R.id.shop_title)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val status = containerView.findViewById<TextView>(R.id.status)
        val index = containerView.findViewById<TextView>(R.id.index)
        val count = containerView.findViewById<TextView>(R.id.count)
        val btMore = containerView.findViewById<TextView>(R.id.bt_more)
    }


    //右边viewholder
    class TransferInViewHolder(val containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<LinearLayout>(R.id.layout_in)
        val shopTitle = containerView.findViewById<TextView>(R.id.shop_title)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val status = containerView.findViewById<TextView>(R.id.status)
        val index = containerView.findViewById<TextView>(R.id.index)
        val count = containerView.findViewById<TextView>(R.id.count)
        val btMore = containerView.findViewById<TextView>(R.id.bt_more)
    }


}