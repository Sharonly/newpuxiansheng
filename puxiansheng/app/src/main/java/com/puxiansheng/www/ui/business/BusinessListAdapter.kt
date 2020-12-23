package com.puxiansheng.www.ui.business

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url

class BusinessListAdapter(
    var mContext: Context,
    var lists: ArrayList<BusinessBean>,
    var callListener: CallListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_invest_business_list_item, parent, false)
            return InfoViewHolder(view)
        }
    }


    fun addList(tempList: ArrayList<BusinessBean>, isClean: Boolean) {
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
        if (holder is InfoViewHolder) {
            var info = lists[position]

            info?.name?.let {
                holder.title.text = it
            }

            info?.thumb_img?.let { url ->
                holder.icon.url(url)
            }

            info?.trades?.let {
                holder.content.text = it
            }

            info?.investment?.let {
                holder.money.text = it
            }

            val spr = StringBuilder()
            info?.keywords?.let {
                it.forEach {
                    spr.append(it)
                    spr.append(" ")
                }
                holder.investLabel.text = spr.toString()
            }

            holder.btConsult.setOnClickListener {
                callListener?.call(info)
            }

            holder.root.setOnClickListener {
                val intent = Intent(
                    mContext,
                    InvestBusinessDetailActivity::class.java
                )
                intent.putExtra("id", info?.id)
                intent.putExtra("url", info?.jump_param)
                mContext.startActivity(intent)
            }
        }
    }


     class InfoViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val root: View = containerView.findViewById(R.id.root)
        val icon: ImageView = containerView.findViewById(R.id.shop_icon)
        val title: TextView = containerView.findViewById(R.id.title)
        val content: TextView = containerView.findViewById(R.id.invest_content)
        val investLabel: TextView = containerView.findViewById(R.id.invest_label)
        val money: TextView = containerView.findViewById(R.id.invest_money)
        val btConsult: TextView = containerView.findViewById(R.id.bt_consult)

    }

    interface CallListener {
        fun call(info: BusinessBean)
    }


}