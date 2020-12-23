package com.puxiansheng.www.ui.message

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.www.R

class NewMessageListAdapter(var mContext: Context, var lists: ArrayList<MessageItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 0) {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_order_list_empty, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        } else {
            val view = LayoutInflater.from(mContext)
                .inflate(R.layout.fragment_message_item, parent, false)
            return InfoViewHolder(view)
        }
    }


    fun addList(tempList: ArrayList<MessageItem>, isClean: Boolean) {
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
            holder.data.text = info?.view_time

            holder.root.setOnClickListener {
                val intent = Intent(mContext, MessageDetailActivity::class.java)
                intent.putExtra("noticeId", info?.messageID.toString())
                mContext.startActivity(intent)
            }
            holder.messageInfo.text = info?.content
            if( info?.read_log_id == 0){
                holder.redPoint.visibility = View.VISIBLE
            }else{
                holder.redPoint.visibility = View.INVISIBLE
            }
        }
     }



     class InfoViewHolder(var containerView: View) : RecyclerView.ViewHolder(containerView) {
        val data:TextView = containerView.findViewById(R.id.message_time)
        val root:View = containerView.findViewById(R.id.layout_message)
        val redPoint :ImageView = containerView.findViewById(R.id.red_point)
        val title:TextView = containerView.findViewById(R.id.message_title)
        val messageInfo:TextView = containerView.findViewById(R.id.message_info)


    }


}