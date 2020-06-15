package com.puxiansheng.www.ui.mine.relase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity

class ReleaseInAdapter(var mContext: Context, var lists: ArrayList<OrderDetailObject>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_orders_mine_transfer_in_item, parent, false)
            return TestViewHolder(view)
        }

    }


    fun addList(tempList: ArrayList<OrderDetailObject>, isClean: Boolean) {
        if (isClean) {
            lists.clear()
        }

        lists.addAll(tempList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (lists.size == 0) 1 else lists.size
    }

    override fun getItemViewType(position: Int): Int {

        if (lists.size == 0) {
            return 0
        }
        return 1
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TestViewHolder) {
            var shopInfo = lists[position]
            holder.shopTitle.text = shopInfo.title
            holder.shopIndustry.text = shopInfo.formattedFinalIndustry
            holder.shopSize.text = shopInfo.view_acreage_un_prefix
            holder.shopRent.text = shopInfo.view_rent_un_prefix
            holder.shopArea.text = shopInfo.formattedFinalLocationNode
            holder.shopData.text = shopInfo.day_time

            holder.edit.setOnClickListener {
                val intent =
                    Intent(mContext, InsertOrUpdateTransferInOrderActivity::class.java)
                intent.putExtra("shopID", shopInfo?.shopID.toString())
                mContext.startActivity(intent)
            }


            holder.root.setOnClickListener {
                val intent = Intent(mContext, TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", shopInfo?.shopID.toString())
                mContext.startActivity(intent)
            }
        }
    }


    inner class TestViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.layout)
        val shopTitle = containerView.findViewById<TextView>(R.id.title)
        val shopIndustry = containerView.findViewById<TextView>(R.id.industry)
        val shopSize = containerView.findViewById<TextView>(R.id.size)
        val shopRent = containerView.findViewById<TextView>(R.id.rent)
        val shopArea = containerView.findViewById<TextView>(R.id.area)
        val shopData = containerView.findViewById<TextView>(R.id.date)
        val delete = containerView.findViewById<TextView>(R.id.bt_delete)
        val edit = containerView.findViewById<TextView>(R.id.bt_edit)

    }

    interface onDelete{
        fun delete()
    }
}