package com.puxiansheng.www.ui.mine.relase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity

class ReleaseOutAdapter(var mContext: Context, var dataList: ArrayList<OrderDetailObject>, var deleteListener: onDeleteListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_orders_mine_transfer_out_item, parent, false)
            return TestViewHolder(view)
        }
    }



    fun addList(tempList: ArrayList<OrderDetailObject>, isClean: Boolean) {
        if (isClean) {
            dataList.clear()
        }
        dataList.addAll(tempList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (dataList.size == 0) 1 else dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (dataList.size == 0) {
            return 0
        }
        return 1
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TestViewHolder) {
            var shopInfo = dataList[position]
            holder.shopIcon.url(shopInfo.image)
            holder.shopTitle.text = shopInfo.title
            holder.shopIndustry.text = shopInfo.categoryAcreage
            holder.shopSize.text = shopInfo.formattedSize
            holder.shopRent.text = shopInfo.formattedRent
            holder.shopArea.text = shopInfo.formattedFinalLocationNode
            holder.shopData.text = shopInfo.day_time

            holder.delete.setOnClickListener {
                deleteListener?.delete(shopInfo)
            }

            holder.edit.setOnClickListener {
                val intent =
                    Intent(mContext, InsertOrUpdateTransferOutOrderActivity::class.java)
                intent.putExtra("shopID", shopInfo?.shopID.toString())
                mContext.startActivity(intent)
            }


            holder.root.setOnClickListener {
                val intent = Intent(mContext, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", shopInfo?.shopID.toString())
                mContext.startActivity(intent)
            }
        }
    }


    inner class TestViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val root:View = containerView.findViewById(R.id.layout)
        val shopIcon: ImageView = containerView.findViewById(R.id.shop_icon)
        val shopTitle:TextView = containerView.findViewById(R.id.title)
        val shopIndustry:TextView = containerView.findViewById(R.id.industry)
        val shopSize:TextView = containerView.findViewById(R.id.size)
        val shopRent:TextView = containerView.findViewById(R.id.rent)
        val shopArea:TextView = containerView.findViewById(R.id.area)
        val shopData:TextView = containerView.findViewById(R.id.date)
        val delete: TextView = containerView.findViewById(R.id.bt_delete)
        val edit:TextView = containerView.findViewById(R.id.bt_edit)

    }

    interface onDeleteListener{
        fun delete(order:OrderDetailObject)
    }
}