package com.puxiansheng.www.ui.info

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url

class NewInfoListAdapter(var mContext: Context, var lists: ArrayList<InfoItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_info_item, parent, false)
            return InfoViewHolder(view)
        }

    }


    fun addList(tempList: ArrayList<InfoItem>, isClean: Boolean) {
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
            holder.title.text = info?.title
            holder.desc.text = info?.micro
            holder.data.text = info?.date
            holder.pageViews.text = info?.pageViews.toString()
            holder.icon.url(info?.image)

            holder.root.setOnClickListener {
                val intent = Intent(mContext, InfoDetailActivity::class.java)
                intent.putExtra("url", info?.jump_param)
                mContext.startActivity(intent)
            }
        }
    }


    inner class InfoViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val root:View = containerView.findViewById(R.id.layout)
        val icon:ImageView = containerView.findViewById(R.id.icon)
        val title:TextView = containerView.findViewById(R.id.title)
        val desc:TextView =  containerView.findViewById(R.id.desc)
        val pageViews:TextView = containerView.findViewById(R.id.page_views)
        val data:TextView = containerView.findViewById(R.id.data)

    }


}