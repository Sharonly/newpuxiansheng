package com.puxiansheng.www.ui.message

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentMessageItemBinding
import kotlinx.android.extensions.LayoutContainer

 class MessageListAdapter(private val onItemSelect: ((infoItem: MessageItem?) -> Unit)? = null) : PagedListAdapter<MessageItem, MessageListAdapter.InfoViewHolder>(
     MessageItem.DIFF
) {
     private var dataList: PagedList<MessageItem>? = null


     inner class InfoViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val binding = FragmentMessageItemBinding.bind(containerView)

        @SuppressLint("SetTextI18n")
        fun bind(infoItem: MessageItem?) {
            binding.messageTitle.text = infoItem?.title
            binding.messageTime.text = infoItem?.view_time
            binding.messageInfo.text = infoItem?.content
            if( infoItem?.read_log_id == 0){
                binding.redPoint.visibility = View.VISIBLE
            }else{
                binding.redPoint.visibility = View.INVISIBLE
            }
            binding.root.setOnClickListener {
                onItemSelect?.let { select -> select(infoItem) }
            }
        }
    }

     override fun submitList(pagedList: PagedList<MessageItem>?) {
         dataList = pagedList!!
         super.submitList(pagedList)
         notifyDataSetChanged()
     }


     fun getDataCount(): Int {
         return dataList?.size ?: 0
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = InfoViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.fragment_message_item, parent, false)
    )

    override fun onBindViewHolder(
        holder: InfoViewHolder,
        position: Int
    ) = holder.bind(getItem(position))


 }