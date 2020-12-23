package com.puxiansheng.www.ui.mine.relase.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.www.R
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import kotlinx.android.extensions.LayoutContainer

class UserOrderStateAdapter(
    var context: Context,
    var dataList: ArrayList<OrderDetailObject>,
    var deleteListener: DeleteListener? = null,
    var refreshListener: RefreshListener? = null
) :
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
                .inflate(R.layout.fragment_orders_public_item, parent, false)
            return UserOrderViewHolder(view)
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
            return 1
        }
        return 0
    }

    @SuppressLint("Range", "ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserOrderViewHolder) {
            var shopInfo = dataList[position]
            println("item---》${shopInfo}")

            holder.shopTitle.text = shopInfo.title
            shopInfo?.isVip.let {
                if (it == 1) {
                    holder.shopIsVip.visibility = View.VISIBLE
                    holder.btDelete.visibility = View.GONE
                    holder.btEdit.visibility = View.GONE
                    holder.btRefresh.visibility = View.GONE
                } else {
                    holder.shopIsVip.visibility = View.INVISIBLE
                    holder.btDelete.visibility = View.VISIBLE
                    holder.btEdit.visibility = View.VISIBLE
                    holder.btRefresh.visibility = View.VISIBLE

                    if(shopInfo.isUpdateTime == 1) {
                        holder.btRefresh.setTextColor(Color.parseColor("#989796"))
                        holder.btRefresh.setBackgroundResource(R.drawable.bg_bt_edit)
                    }else{
                        holder.btRefresh.setTextColor(Color.parseColor("#F78934"))
                        holder.btRefresh.setBackgroundResource(R.drawable.bg_bt_delete)
                    }
                }

                shopInfo?.state?.let { status ->
                    holder.shopStatus.text = status?.text
                    holder.shopStatus.setTextColor(Color.parseColor(status?.color))

                    if (status?.text == "已发布" || status?.text == "已过期" || status?.text == "在审核") {
                        holder.btRefresh.visibility = View.VISIBLE
                    } else {
                        holder.btRefresh.visibility = View.GONE
                    }

                    if (status?.text == "已下架" || status?.text == "已完结" || status?.text == "已成交") {
                        holder.btEdit.visibility = View.GONE
                    } else {
                        holder.btEdit.visibility = View.VISIBLE
                    }
                }
            }


            shopInfo?.area_point_str.let { it ->
                holder.shopArea.text = it
            }

            shopInfo?.view_rent_un_prefix.let { it ->
                holder.shopRent.text = it
            }

            shopInfo?.view_acreage_un_prefix.let { it ->
                holder.shopSize.text = it
            }

            shopInfo?.data_type.let { shop ->
                if (shop == "transfer_shop") {
                    holder.shopType.setImageResource(R.mipmap.ic_transfer_out)
                } else if (shop == "find_shop") {
                    holder.shopType.setImageResource(R.mipmap.ic_transfer_in)
                }

                holder.btEdit.setOnClickListener {
                    if (shop == "transfer_shop") {
                        val intent =
                            Intent(context, InsertOrUpdateTransferOutOrderActivity::class.java)
                        intent.putExtra("shopID", shopInfo.shopID?.toString())
                        context.startActivity(intent)
                    } else if (shop == "find_shop") {
                        val intent =
                            Intent(context, InsertOrUpdateTransferInOrderActivity::class.java)
                        intent.putExtra("shopID", shopInfo.shopID?.toString())
                        context.startActivity(intent)
                    }
                }

                holder.root.setOnClickListener {
                    if (shop == "transfer_shop") {
                        val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
                        intent.putExtra("shopID", shopInfo.shopID?.toString())
                        context.startActivity(intent)
                    } else if (shop == "find_shop") {
                        val intent = Intent(context, TransferInOrderDetailActivity::class.java)
                        intent.putExtra("shopID", shopInfo.shopID?.toString())
                        context.startActivity(intent)
                    }
                }

            }

            holder.btRefresh.setOnClickListener {
                refreshListener?.refresh(shopInfo)
            }

            holder.btDelete.setOnClickListener {
                deleteListener?.delete(shopInfo)
            }
        }
    }


    abstract inner class OrderViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        abstract fun bind(item: OrderDetailObject?)
    }


     class UserOrderViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {
        val root = containerView.findViewById<View>(R.id.item_layout)
        val shopIsVip: ImageView = containerView.findViewById(R.id.is_vip)
        val shopStatus: TextView = containerView.findViewById(R.id.status)
        val shopDesc: TextView = containerView.findViewById(R.id.desc)
        val shopTitle: TextView = containerView.findViewById(R.id.title)
        val shopSize: TextView = containerView.findViewById(R.id.size)
        val shopRent: TextView = containerView.findViewById(R.id.rent)
        val shopArea: TextView = containerView.findViewById(R.id.area)
        val btDelete: ImageView = containerView.findViewById(R.id.bt_delete)
        val btRefresh: TextView = containerView.findViewById(R.id.bt_refresh)
        val btEdit: TextView = containerView.findViewById(R.id.bt_edit)
        val shopType: ImageView = containerView.findViewById(R.id.ic_type)

    }


    //空viewholder
    inner class EmptyOrderViewHolder(override val containerView: View) :
        UserOrderStateAdapter.OrderViewHolder(containerView) {

        override fun bind(item: OrderDetailObject?) {

        }
    }

    interface DeleteListener {
        fun delete(order: OrderDetailObject)
    }

    interface RefreshListener {
        fun refresh(order: OrderDetailObject)
    }


}